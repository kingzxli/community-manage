package com.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.community.entity.Result;
import com.community.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "redis")
@RestController
public class RedisController {
	
	@Autowired
	UserMapper userMapper;
	
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作字符串【常用】

   // @Autowired
   // RedisTemplate redisTemplate;//操作k-v都是对象

//    @Autowired
//    RedisTemplate<Object,User> empRedisTemplate;
	
	@ApiOperation(value = "单记录查询")
	@GetMapping("/redis")
	public Result<?> redis() {	
		
		System.out.println("测试redis");
	       // stringRedisTemplate.opsForValue().append("msg", "hello");
	       // String msg = stringRedisTemplate.opsForValue().get("msg");

	       // System.out.println(msg);
//		
//		  User emp = userMapper.selectById(1);
//	        empRedisTemplate.opsForValue().set("emp-01", emp);
		return new Result<>().SUCCESS;
	}
	
}
