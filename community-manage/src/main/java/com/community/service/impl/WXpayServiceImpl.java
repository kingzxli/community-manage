package com.community.service.impl;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.community.exception.CustomException;
import com.community.service.WXpayService;
import com.community.util.IdMaker;
import com.github.wxpay.sdk.WXPayUtil;

@Service
public class WXpayServiceImpl implements WXpayService{
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

	@Override
	public Map<String,String> wxpay(BigDecimal totalFee,String openId) {	
		//将金额(元)转换为分
		Assert.notNull(totalFee, "金额不能为空");
		
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
			
			requestDataMap.put("appid", APPID); //公众号id
			requestDataMap.put("body", "payMoney"); //商品描述				
			requestDataMap.put("mch_id", MCHID); //商户号					
			requestDataMap.put("nonce_str", nonceStr); //随机字符串
			requestDataMap.put("notify_url", NOTIFYURL); //异步接受回调用地址，url必须为外网可访问路径
			requestDataMap.put("openid", openId);
			requestDataMap.put("out_trade_no", IdMaker.get()); //商户订单号
			requestDataMap.put("spbill_create_ip", hostAddress); //Native 支付填调用微信支付API的机器IP
			requestDataMap.put("total_fee", ""+amountInt); //订单金额,单位为分						
			requestDataMap.put("trade_type", "JSAPI"); //交易类型	
			//requestDataMap.put("product_id", IdMaker.get()); //商品Id
			
				
			//签名
			String signinValue = WXPayUtil.generateSignature(requestDataMap, KEY);
			
			requestDataMap.put("sign", signinValue); //签名			
			//requestDataMap.put("time_stamp", ""+new Date().getTime());
			System.out.println("===========" + requestDataMap);
			//设置参数 xml 格式
			String requestDataXml = WXPayUtil.mapToXml(requestDataMap);
			String responseDataXml = this.doPostByXml(WXPAYURL, requestDataXml);
			System.out.println("===========" + responseDataXml);
			//将xml转换为map集合
			responseDataMap = WXPayUtil.xmlToMap(responseDataXml);		
			
			//判断支付结果	
			Assert.isTrue(responseDataMap != null && "SUCCESS".equals(responseDataMap.get("return_code")), "支付通讯失败");		
			Assert.isTrue("SUCCESS".equals(responseDataMap.get("result_code")), "支付失败");
			System.out.println("返回参数");
			System.out.println(responseDataMap);

			
			/**
			 * 参数返回前端
			 */
			String prepay_id = "";//预支付id
			 
			//支付通讯成功
			prepay_id = responseDataMap.get("prepay_id");		 
			Map<String, String> payMap = new HashMap<String, String>();
			 
			payMap.put("appId", APPID);
			 
			payMap.put("timeStamp", new Date().getTime()+"");
			 
			payMap.put("nonceStr", WXPayUtil.generateNonceStr());
			 
			payMap.put("signType", "MD5");
			 
			payMap.put("package", "prepay_id=" + prepay_id);
			 
			String paySign= WXPayUtil.generateSignature(payMap, KEY);
			payMap.put("paySign", paySign);
			
			return payMap;
			
			
		} catch (Exception e) {			
			throw new CustomException("微信支付参数解析错误");
		}
		
	
		
			
		 
		
		 
		
		
		//return responseDataMap;
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
	
	
}
