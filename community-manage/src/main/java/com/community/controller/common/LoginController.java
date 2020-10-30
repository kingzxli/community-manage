package com.community.controller.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.community.entity.common.Result;
import com.community.entity.common.User;
import com.community.mapper.common.UserMapper;
import com.community.util.Assert;
import com.community.util.IdMaker;
import com.community.util.SendSms;
import com.community.util.redis.RedisUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "注册&&登录")
@RestController
public class LoginController {		
	
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserMapper userMapper;
	
	@ApiOperation(value = "发送验证码")
	@GetMapping("/sms/sendCode")
	public Result<?> sendCode(String phone) {
		String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
		SendSms.sendCode(phone,code);
		redisUtil.set(phone, code, 600);
		return Result.SUCCESS;
	}
	
	@ApiOperation(value = "登录(openId 或者 手机+验证码)")
	@GetMapping("/doLogin")
	public Result<Map<String, Object>> sendCode(User user) {
		String openId = user.getOpenId();
		String phone = user.getPhone();
		String code = user.getCode();
		
		User dbUser = null;
		if(StringUtils.isNotEmpty(openId)) {
			//openId登录
			dbUser =  userMapper.selectOne(user);			
		}else if(StringUtils.isNotEmpty(phone) && StringUtils.isNotEmpty(code)){
			//手机 + 验证码登录			
			String dbCode =  (String)redisUtil.get(phone);
			if(code.equals(dbCode)) {
				dbUser =  userMapper.selectOne(user);			
			}
		}		

		//登录成功
		if(dbUser != null) {
			Map<String, Object> result = new HashMap<>();
			String token = IdMaker.getSnowflakeId();
			System.out.println("===token===" + token);
			//设置token过期时间12小时
			redisUtil.oSet(token, user,720);

			result.put("token", token);
			result.put("user", dbUser);
			return new Result<>(result);
		}else {
			return new Result<>(null);
		}	 
	}
	
	@ApiOperation(value = "注册")
	@PostMapping("/register")
	public Result<?> register(@RequestBody User user) {	
		String openId = user.getOpenId();
		String phone = user.getPhone();
		String code = user.getCode();
		String dbCode =  (String)redisUtil.get(phone);
		
		Assert.notEmpty(openId,"openId不能为空");
		Assert.notEmpty(phone,"手机号不能为空");
		Assert.notEmpty(code,"验证码不能为空");
		Assert.isTrue(code.equals(dbCode),"验证码错误");
		
		//查询是否有人员信息
		User para = new User();
		para.setOpenId(openId);
		User dbUser = userMapper.selectOne(para);		
		Assert.isNull(dbUser, "您已有注册账户");
		
		user.setId(IdMaker.get());
		user.setIsDelete(0);
		user.setRoleId(3);
		user.setCreatedTime(new Date());
		userMapper.insert(user);	

		return Result.SUCCESS;
	}
	
}
