package com.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.community.entity.Result;
import com.community.entity.User;
import com.community.mapper.UserMapper;
import com.community.util.RedisUtil;
import com.community.util.SendSms;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "登录")
@RestController
public class LoginController {		
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserMapper userMapper;
	
	@ApiOperation(value = "发送验证码")
	@GetMapping("/sms/sendCode")
	public Result<?> sendCode(String phone) {
		
		String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));// 随机生成四位数
		//发送短信
		SendSms.sendCode(phone,code);
		//缓存
		redisUtil.set(phone, code, 600);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "登录")
	@GetMapping("/login")
	public Result<?> sendCode(String openId,String phone,String code) {
		
		//User user = userMapper.list(openId,phone,code);
	
		return Result.SUCCESS;
	}
	

}
