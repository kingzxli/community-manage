package com.community.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import com.community.entity.WechatProperties;
import io.swagger.annotations.ApiOperation;

@RestController
public class WXLogin {
	
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
	
	@Value("${appSecret}")
	private String APPSECRET;
	
	@Value("${wx.redirect.url}")
	private String redirectUrl;
	
	@Value("${wx.response.url}")
	private String responseUrl;
	
//	@Value("${wx.getUserAccessTokenUrl}")
//	private String getUserAccessTokenUrl;
	
	@Autowired
	private WechatProperties wechatProperties;
	@Autowired
	private RestTemplate restTemplate;
	
	   /**
	    * Tea微信登录接口
	    * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx23db2e5b441b70fc&redirect_uri=http://changshengwuye.cn/index.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
		* code=031jd9000Do3nK1CKt200OLvoH3jd90R&state=STATE
	    * 
	    * @throws IOException 
	    */
	   @ApiOperation(value = "微信登录接口")
	   @RequestMapping("/login")
	   public void wxLogin(HttpServletResponse response) throws IOException{
		   String redirectUri = URLEncoder.encode(redirectUrl, "UTF-8");
	       String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
	               "appid=APPID" +
	               "&redirect_uri=REDIRECT_URI"+
	               "&response_type=code" +
	               "&scope=SCOPE" +
	               "&state=123#wechat_redirect";	      
	       String url2 = url.replace("APPID",APPID).replace("REDIRECT_URI",redirectUri).replace("SCOPE","snsapi_userinfo");
	       System.out.println("===获取code===" + url2);
	       response.sendRedirect(url2);
	   }
	
	   @ApiOperation(value = "微信授权回调接口")
	   @RequestMapping("/callBack")
	   protected void deGet(HttpServletRequest request, HttpServletResponse response)throws Exception {
		   //获取回调地址中的code
		   String code = request.getParameter("code");		   
		   String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret="
				   + APPSECRET + "&code=" + code + "&grant_type=authorization_code";
		   System.out.println("==获取用户信息url===" + url);
		   JSONObject jsonObject = this.doGetJson(url);
		   //1.获取微信用户的openid
		   String openid = jsonObject.getString("openid");		  
		   //2.获取获取access_token
		   String access_token = jsonObject.getString("access_token");
		   String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid
				   + "&lang=zh_CN";
		   //3.获取微信用户信息
		   JSONObject userInfo = this.doGetJson(infoUrl);
		   //至此拿到了微信用户的所有信息,剩下的就是业务逻辑处理部分了
		   //保存openid和access_token到session
		   System.out.println("==用户信息===" + userInfo);
		//   request.getSession().setAttribute("openid", openid);
		//   request.getSession().setAttribute("access_token", access_token);
		   //去数据库查询此微信是否绑定过手机
//		   User user = userService.queryByOpenId(openid);
//		   String mobile=user==null?"":user.getMobile();
		 response.sendRedirect(responseUrl);
//		   if(null == mobile || "".equals(mobile)){
//			   //如果无手机信息,则跳转手机绑定页面
//			   response.sendRedirect("/front/register.html");
//		   }else{
//			   //否则直接跳转首页
//			   response.sendRedirect("/front/index.html");
//		   }
	   }	



	   
	   
	   
	

	public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.parseObject(result);
		}
		httpGet.releaseConnection();
		return jsonObject;
	}
		
}
