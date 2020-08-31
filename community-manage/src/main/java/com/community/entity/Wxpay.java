package com.community.entity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class Wxpay implements Serializable{

	private static final long serialVersionUID = 1L;

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
}
