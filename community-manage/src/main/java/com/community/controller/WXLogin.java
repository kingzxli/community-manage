package com.community.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/index/{name}")
	public List<String> index(@PathVariable(name="name")String name) {
		List<String> list = new ArrayList<>();
		list.add(name);
		list.add(name+"2号");
		return list;
	}
	
	@RequestMapping("/admin/login")
	public void login(HttpServletResponse response) {		
		try {
			//这里是回调的url
			String redirect_uri = URLEncoder.encode(NOTIFYURL, "UTF-8");			
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid=APPID" +
                    "&redirect_uri=REDIRECT_URI&response_type=code" +
                    "&scope=SCOPE" +
                    "#wechat_redirect";
			String url2 = url.replace("APPID",APPID).replace("REDIRECT_URI",redirect_uri).replace("SCOPE","snsapi_userinfo");
			response.sendRedirect(url2);
		} catch (IOException e) {					
			e.printStackTrace();
		}
	}
	

//	@GetMapping("/index")
//	public void index(String code) {
//		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
//		//开始请求url地址，第一次请求通过code获取openid与access_token     
//		UrlConnUtils.get(url.replace("APPID", "你的APPID").replace("SECRET", "你的SECRET")
//				.replace("CODE", code), new GetDataUrlConnListener() {                
//			@Override
//			public void onSuccess(HttpURLConnection connection) throws IOException {
//				String data = MyUtils.inputStreamToString(connection.getInputStream());
//				JSONObject jsonObject = JSONObject.parseObject(data);
//				if (jsonObject.getString("openid") != null) {
//					//拉取用户信息
//					String openid = jsonObject.getString("openid");
//					String access_token = jsonObject.getString("access_token");
//					String url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
//					//第二次请求，用openid与access_token获取用户的信息
//					UrlConnUtils.get(url.replace("OPENID", openid).replace("ACCESS_TOKEN", access_token), new GetDataUrlConnListener() {
//						@Override
//						public void onSuccess(HttpURLConnection connection) throws IOException {
//							String data = MyUtils.inputStreamToString(connection.getInputStream());                                   
//							System.out.println(data);//输出微信返回的用户信息
//						}
//					}
//				});
//			}
	
		
}
