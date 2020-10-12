//package com.community.controller;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import com.alibaba.fastjson.JSONObject;
//import com.community.entity.Wxpay;
//import com.community.util.Assert;
//import io.swagger.annotations.ApiOperation;
//
//@RestController
//public class WXLogin {
//
//	@Autowired
//	private Wxpay wxpay;
//	@Autowired
//	private RestTemplate restTemplate;
//		
//	@ApiOperation(value = "获取openID")
//	@GetMapping("/login")
//	public void wxLogin(HttpServletResponse response) throws IOException{
//		String redirectUri = URLEncoder.encode(wxpay.getRedirectUrl(), "UTF-8");
//		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
//				"appid=APPID" +
//				"&redirect_uri=REDIRECT_URI"+
//				"&response_type=code" +
//				"&scope=SCOPE" +
//				"&state=123#wechat_redirect";	      
//		String url2 = url.replace("APPID",wxpay.getAppId()).replace("REDIRECT_URI",redirectUri).replace("SCOPE","snsapi_base");
//		response.sendRedirect(url2);
//	}
//	
//
//	@RequestMapping("/callBack")
//	protected void deGet(HttpServletRequest request, HttpServletResponse response)throws Exception {
//		//获取回调地址中的code
//		String code = request.getParameter("code");		   
//		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wxpay.getAppId() + "&secret="
//				+ wxpay.getAppSecret() + "&code=" + code + "&grant_type=authorization_code";
//
//		//JSONObject jsonObject = this.doGetJson(url);
//		ResponseEntity<String> result = restTemplate.postForEntity(url, null, String.class);
//		Assert.notNull(result, "=============获取openId失败==========");
//		
//		JSONObject jsonObject = JSONObject.parseObject(result.getBody());
//		
//		//1.获取微信用户的openid
//		String openId = jsonObject.getString("openid");		
//
//		//2.获取获取access_token
//		//		   String access_token = jsonObject.getString("access_token");
//		//		   String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openId
//		//				   + "&lang=zh_CN";
//		//		   //3.获取微信用户信息
//		//		   JSONObject userInfo = this.doGetJson(infoUrl);
//		//		   System.out.println("==用户信息===" + userInfo);
//
//		//至此拿到了微信用户的所有信息,剩下的就是业务逻辑处理部分了		  
//		request.getSession().setAttribute("openId", openId);		
//		//request.getSession().setAttribute("access_token", access_token);
//		System.out.println("===用户openId===" + openId);
//	}		   
//		
//}
