package com.community.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.community.entity.Result;
import com.community.util.SendSms;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "登录")
@RestController
public class LoginController {		
	
	@ApiOperation(value = "发送验证码")
	@GetMapping("/sms/sendCode")
	public Result<?> insert(String phoneNumber) {
		
		String code = (Math.random()*9+1)*100000 + "";
		SendSms.sendSms(phoneNumber, code, "");
		return Result.SUCCESS;
	}
	

}
