package com.community.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.community.entity.User;
import com.community.entity.WechatProperties;
import com.community.mapper.UserMapper;
import com.community.service.UserService;
import com.community.util.Assert;
import com.community.util.IdMaker;

@RestController
public class WXLogin {
	private static final String OPENID_KEY = "openid";
	private static final String ACCESS_TOKEN = "access_token";
	
	@Value("${appid}")
	private String APPID;
	
	@Value("${mch_id}")
	private String MCHID;
	
	@Value("${notify_url}")
	private String NOTIFYURL;
	
	@Value("${type}")
	private String TYPE;
	
	@Value("${key}")
	private String KEY;
	
	@Value("${wxpay_url}")
	private String WXPAYURL;
	
	@Value("${wx.redirect.url}")
	private String redirectUrl;
	
	@Value("${wx.response.url}")
	private String responseUrl;
	
	@Autowired
	private WechatProperties wechatProperties;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * https://open.weixin.qq.com/connect/oauth2/authorize?
	 * appid=%s&
	 * redirect_uri=http://bzt.fmcgnet.com/rest/wechat/getWechatUserInfoCallback&response_type=code
	 * &scope=snsapi_userinfo
	 * &state=123#wechat_redirect
	 * @return
	 */
	@RequestMapping("/admin/login")
	public Object login() {	
		String url2 = null;
		try {
			//这里是回调的url
			//String redirect_uri = URLEncoder.encode(NOTIFYURL, "UTF-8");	
			String redirect_uri = "http://localhost:8081/getWechatUserInfoCallback&response_type=code";
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid=APPID" +
                    "&redirect_uri=REDIRECT_URI&response_type=code" +
                    "&scope=SCOPE" +
                    "&state=123#wechat_redirect";
			url2 = url.replace("APPID",APPID).replace("REDIRECT_URI",redirect_uri).replace("SCOPE","snsapi_userinfo");
		} catch (Exception e) {					
			e.printStackTrace();
		}
		return restTemplate.postForEntity(url2, null, String.class);
	}
	
	@RequestMapping("/getWechatUserInfoCallback")
	public void getWechatUserInfoCallback(HttpServletResponse response, String code) throws IOException {
		String openIdUrl = String.format(wechatProperties.getGetWechatUserOpenIdUrl(), wechatProperties.getAppId(), wechatProperties.getAppSecret(), code);
		System.out.println();
		System.out.println("=============openIdUrl: " + openIdUrl);
		ResponseEntity<String> result = restTemplate.postForEntity(openIdUrl, null, String.class);
		Assert.notNull(result, "=============获取access_token失败...");
		
		JSONObject json = JSONObject.parseObject(result.getBody());
		System.out.println("==========获取openId: " + json);
		Assert.isTrue(json.containsKey("openid"), "=============获取openId失败, openId为空...");
		/**
		 * 生成缓存token(缓存有效时间6小时)  6小时 = 240分
		 */
		String token = IdMaker.getSnowflakeId();
		// 缓存openId
		//redisService.add(RedisConstant.WECHAT_OPENID_PREFIX + token, json.getString(OPENID_KEY));
		// 以openId作为key缓存access_token
		//redisService.add(RedisConstant.WECHAT_USER_ACCESS_TOKEN + json.getString(OPENID_KEY), json.getString(ACCESS_TOKEN));
		
		this.getUser(json.getString(OPENID_KEY), response);
		
		// response.sendRedirect(wechatProperties.getRedirectUrl() + token);
		// String url = "https://marketouchplus-uat.brandmax.com.cn/h5/#/pages/centre/centre?token=";
		response.sendRedirect(responseUrl + token);
	}
	
	private User getUser(String openid, HttpServletResponse response) throws IOException {
		QueryWrapper<User> params = new QueryWrapper<>();
		params.lambda().eq(User::getOpenId, openid)//
			.orderByAsc(User::getCreatedTime);
		List<User> list = userMapper.selectList(params);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		//
		JSONObject resultInfo = this.getWxUserInfoV2(openid);
		Assert.notNull(resultInfo, "调用微信接口获取用户信息失败");
		try {
			System.out.println(resultInfo.toJSONString());
		} catch(Exception e) {
			
		}
		/**
		 * 将获取到的微信用户信息存入数据库
		 */
		User user = new User();
		user.setId(IdMaker.get());
		user.setName(resultInfo.getString("nickname"));
		
		if(StringUtils.isBlank(user.getName())) {
			System.out.println("获取微信用户信息失败: " + resultInfo.toString());
			//response.sendRedirect("https://marketouchplus-uat.brandmax.com.cn/jump/login.html");
			response.sendRedirect(redirectUrl);
			return null;
		}
		
		user.setOpenId(resultInfo.getString("openid"));
		user.setWxName(resultInfo.getString("nickname"));		
		user.setCreatedUser(resultInfo.getString("nickname"));
		user.setCreatedTime(new Date());		
		String sexKey = "sex";
		if(resultInfo.get(sexKey) != null) {
		    user.setGender(Integer.parseInt(resultInfo.getString(sexKey)));
		}
//		user.setHeadImg(resultInfo.getString("headimgurl"));
//		
//		wechatUserService.save(user);
//		
//		WechatUserDetail userDetail = new WechatUserDetail();
//		userDetail.setEmployeeId(user.getId());
//		wechatUserService.addEmployeeManageDetail(userDetail);
		return user;
	}
	
	/**
	 * 使用sns模式获取微信用户信息
	 * @author Tan Ling
	 * @date 2019年9月5日 下午3:18:16
	 * @param openId
	 * @return
	 */
	public JSONObject getWxUserInfoV2(String openId) {
//		String userAccessToken = (String) redisService.get(RedisConstant.WECHAT_USER_ACCESS_TOKEN + openId);
//		Assert.notEmpty(userAccessToken, "获取用户信息失败, 访问令牌已失效");
//
//		String url = String.format(wechatProperties.getGetWechatUserInfoUrl(), userAccessToken, openId);
//		ResponseEntity<String> responseEntity = getRestTemplate().getForEntity(url, String.class);
//		if (responseEntity == null) {
//			LogUtil.printLog("调用微信接口获取用户信息失败");
//			throw new CustomException("调用微信接口获取用户信息失败");
//		}
//		JSONObject userObj = JSONObject.parseObject(responseEntity.getBody());
//		/**
//		 * 微信access_token只能存在一个, 如果token过期重新获取一次token(处理开发环境和生产环境使用同一个公众号的问题)
//		 */
//		LogUtil.printLog("=============调用微信接口获取微信用户信息: " + userObj);
//		Assert.isTrue(userObj.containsKey("nickname"), "获取用户信息失败");
//
//		return userObj;
		return null;
	}
		
}
