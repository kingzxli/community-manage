package com.community.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信需要用到的配置信息
 */
@Component
@ConfigurationProperties(prefix="wechat")
public class WechatProperties {
	/**
	 * appId
	 */
	private String appId;
	/**
	 * appSecret
	 */
	private String appSecret;
	/**
	 * 微信回调跳转链接
	 */
	private String redirectUrl;
	/**
	 * 消息推送链接
	 */
	private String messageTemplateUrl;
	/**
	 * 获取openId链接
	 */
	private String getOpenIdUrl;
	/**
	 * 获取access_token链接
	 */
	private String getAccessTokenUrl;
	/**
	 * 获取用户信息使用(需要传递回调接口)
	 */
	private String getUserAccessTokenUrl;
	/**
	 * 获取微信用户信息链接
	 */
	private String getWechatUserUrl;
	/**
	 * 获取微信用户openId(网页授权方式)
	 */
	private String getWechatUserOpenIdUrl;
	/**
	 * 获取微信用户信息
	 */
	private String getWechatUserInfoUrl;
	/**
	 * jsApiTicket
	 */
	private String jsApiTicket;
	/**
	 * 长链接转短链接接口地址
	 */
	private String long2ShortUrl;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getMessageTemplateUrl() {
		return messageTemplateUrl;
	}

	public void setMessageTemplateUrl(String messageTemplateUrl) {
		this.messageTemplateUrl = messageTemplateUrl;
	}

	public String getGetOpenIdUrl() {
		return getOpenIdUrl;
	}

	public void setGetOpenIdUrl(String getOpenIdUrl) {
		this.getOpenIdUrl = getOpenIdUrl;
	}

	public String getGetAccessTokenUrl() {
		return getAccessTokenUrl;
	}

	public void setGetAccessTokenUrl(String getAccessTokenUrl) {
		this.getAccessTokenUrl = getAccessTokenUrl;
	}

	public String getGetWechatUserUrl() {
		return getWechatUserUrl;
	}

	public void setGetWechatUserUrl(String getWechatUserUrl) {
		this.getWechatUserUrl = getWechatUserUrl;
	}

	public String getJsApiTicket() {
		return jsApiTicket;
	}

	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}

	public String getGetUserAccessTokenUrl() {
		return getUserAccessTokenUrl;
	}

	public void setGetUserAccessTokenUrl(String getUserAccessTokenUrl) {
		this.getUserAccessTokenUrl = getUserAccessTokenUrl;
	}

	public String getGetWechatUserInfoUrl() {
		return getWechatUserInfoUrl;
	}

	public void setGetWechatUserInfoUrl(String getWechatUserInfoUrl) {
		this.getWechatUserInfoUrl = getWechatUserInfoUrl;
	}

	public String getGetWechatUserOpenIdUrl() {
		return getWechatUserOpenIdUrl;
	}

	public void setGetWechatUserOpenIdUrl(String getWechatUserOpenIdUrl) {
		this.getWechatUserOpenIdUrl = getWechatUserOpenIdUrl;
	}

	public String getLong2ShortUrl() {
		return long2ShortUrl;
	}

	public void setLong2ShortUrl(String long2ShortUrl) {
		this.long2ShortUrl = long2ShortUrl;
	}
	
}
