package com.community.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

/**
 * 发送短信
 * @author king
 *
 */
public class SendSms {
	static final Integer APPID = 1400436905;
	static final String APPKEY = "64f6937c9e39a2d70e8e7ee0de996801";
	static final Integer TEMPLATEID = 743617;
	static final String SMSSIGN = "昌盛物业";		
	
	public static void sendCode(String phone,String code) {
		try {
			
			String[] params = {code,"10"};
			SmsSingleSender ssender = new SmsSingleSender(APPID, APPKEY);
			SmsSingleSenderResult result = ssender.sendWithParam("86", phone, TEMPLATEID, params, SMSSIGN, "", "");
			System.out.println("===短信结果===" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
