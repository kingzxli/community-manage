package com.community.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

public class SendSms {
	static final Integer APPID = 1400436905;
	static final String APPKEY = "64f6937c9e39a2d70e8e7ee0de996801";
	static final Integer TEMPLATEID = 743538;
	static final String SMSSIGN = "昌盛物业";
	
	public static void sendSms(String phone, String code,String min) {
		try {
			String[] params = {"家和小区",code};
			SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
			SmsSingleSenderResult result = ssender.sendWithParam("86", phone, TEMPLATEID, params, SMSSIGN, "", "");
			System.out.println("===短信结果===" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
