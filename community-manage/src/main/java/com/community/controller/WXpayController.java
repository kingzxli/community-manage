package com.community.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;
import com.community.entity.Wxpay;
import com.community.exception.CustomException;
import com.community.util.Asserter;
import com.community.util.IdMaker;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.Api;

@Api(tags = "微信支付")
@RestController
public class WXpayController {	
	
	@Autowired
	private Wxpay wxpay;
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/wxpay")
	public Map<String,String> wxpay(String code,BigDecimal totalFee) throws IOException {	
		Asserter.notEmpty(code, "code不能为空");
		Asserter.notNull(totalFee, "金额不能为空");	
		/**
		 * 获取openId
		 */
		String openId = this.getOpenId(code);
		Asserter.notEmpty(openId, "微信支付openId获取失败");					
		/**
		 * 微信支付统一下单
		 */
		Map<String,String> map = this.wxpayRequest(totalFee,openId);
		return map;
		
	}
	
	
	private Map<String,String> wxpayRequest(BigDecimal totalFee,String openId) {			
		//将金额(元)转换为分
		BigDecimal amount = totalFee.multiply(new BigDecimal(100));
		Integer amountInt = amount.intValue();
		
		Map<String,String> responseDataMap = null;		
		try {
			Map<String,String> requestDataMap = new HashMap<>();
			//获取本机地址
			InetAddress localhost = InetAddress.getLocalHost();
			String hostAddress = localhost.getHostAddress();
			//生成随机字符串
			String nonceStr = WXPayUtil.generateNonceStr();						
			
			requestDataMap.put("appid", wxpay.getAppId()); //公众号id
			requestDataMap.put("body", "payMoney"); //商品描述				
			requestDataMap.put("mch_id", wxpay.getMchId()); //商户号					
			requestDataMap.put("nonce_str", nonceStr); //随机字符串
			requestDataMap.put("notify_url", wxpay.getNotifyUrl()); //异步接受回调用地址，url必须为外网可访问路径
			requestDataMap.put("openid", openId);
			requestDataMap.put("out_trade_no", IdMaker.get()); //商户订单号
			requestDataMap.put("spbill_create_ip", hostAddress); //Native 支付填调用微信支付API的机器IP
			requestDataMap.put("total_fee", ""+amountInt); //订单金额,单位为分						
			requestDataMap.put("trade_type", "JSAPI"); //交易类型	
			//requestDataMap.put("product_id", IdMaker.get()); //商品Id			
				
			//签名
			String signinValue = WXPayUtil.generateSignature(requestDataMap, wxpay.getKey());
			
			requestDataMap.put("sign", signinValue); //签名			
			//设置参数 xml 格式
			String requestDataXml = WXPayUtil.mapToXml(requestDataMap);
			String responseDataXml = this.doPostByXml(wxpay.getWxpayUrl(), requestDataXml);
			System.out.println("===========" + responseDataXml);
			//将xml转换为map集合
			responseDataMap = WXPayUtil.xmlToMap(responseDataXml);		
			
			//判断支付结果	
			Asserter.isTrue(responseDataMap != null && "SUCCESS".equals(responseDataMap.get("return_code")), "支付通讯失败");		
			Asserter.isTrue("SUCCESS".equals(responseDataMap.get("result_code")), "支付失败");
			System.out.println(responseDataMap);
			
			/**
			 * 参数返回前端
			 */
			//支付通讯成功
			String prepay_id = responseDataMap.get("prepay_id");		 
			Map<String, String> payMap = new HashMap<String, String>();			 
			payMap.put("appId", wxpay.getAppId());		 
			payMap.put("timeStamp", new Date().getTime()+"");		 
			payMap.put("nonceStr", WXPayUtil.generateNonceStr());			 
			payMap.put("signType", "MD5");			 
			payMap.put("package", "prepay_id=" + prepay_id);
			 
			String paySign= WXPayUtil.generateSignature(payMap, wxpay.getKey());
			payMap.put("paySign", paySign);
			
			return payMap;						
		} catch (Exception e) {			
			throw new CustomException("微信支付参数解析错误");
		}		
	}

	/**
	 * 请求参数为xml的post请求
	 * @param url
	 * @param requestDataXml
	 * @return
	 */
	private String doPostByXml(String url,String requestDataXml) {
		
		//创建httpClient连接对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建post请求连接对象
		HttpPost httpPost = new HttpPost(url);
		//创建连接请求参数并设置
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(15000)  //连接服务器主机超时时间
				.setConnectionRequestTimeout(6000) //连接请求超时时间
				.setSocketTimeout(60000) //设置读取响应数据超时时间
				.build();
		
		//为httppost 设置参数
		httpPost.setConfig(requestConfig);
		
		//将上传参数放到entity中
		httpPost.setEntity(new StringEntity(requestDataXml, "UTF-8"));
		//添加头信息
		httpPost.addHeader("Content-Type", "text/xml");
		String result = "";
		try {
			//发送请求
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			//从响应中获取返回内容
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return result;		
	}
	
	/**
	 * 获取openId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private String getOpenId(String code){
		//获取回调地址中的code
		System.out.println("===开始获取openId===");

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + wxpay.getAppId() + "&secret="
				+ wxpay.getAppSecret() + "&code=" + code + "&grant_type=authorization_code";
	
		ResponseEntity<String> result = restTemplate.postForEntity(url, null, String.class);
		Asserter.notNull(result, "=============获取openId失败==========");
		
		JSONObject jsonObject = JSONObject.parseObject(result.getBody());				
		String openId = jsonObject.getString("openid");		
		System.out.println("===openId===" + openId);
		return openId;	  
	}		   

}
