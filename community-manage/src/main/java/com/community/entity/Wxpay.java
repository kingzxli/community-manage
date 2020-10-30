package com.community.entity;

import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix="wxpay")
public class Wxpay implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 公众账号ID	
	 */
	private String appId;
	/**
	 * 商户号
	 */
	private String mchId;
	/**
	 * 通知地址	
	 */
	private String notifyUrl;
	/**
	 * 
	 */
	private String key;
	/**
	 * 统一下单地址
	 */
	private String wxpayUrl;
	/**
	 * 商户密钥
	 */
	private String appSecret;
	/**
	 * 获取用户信息回调地址
	 */
	private String redirectUrl;
	/**
	 * 获取用户信息成功转发地址
	 */
	private String responseLoginUrl;
	/**
	 * 获取用户信息失败转发地址
	 */
	private String responseRegisterUrl;
	private String test;
}
