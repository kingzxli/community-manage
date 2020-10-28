package com.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootTest
class CommunityManageApplicationTests {

	 @Autowired
	 StringRedisTemplate stringRedisTemplate;//操作字符串【常用】
	 /**
     * redis的操作
     *
     */
    @Test
    public void test01(){
    	System.out.println("测试redis");
    	stringRedisTemplate.opsForValue().append("msg", "hello");
	    String msg = stringRedisTemplate.opsForValue().get("msg");

	    System.out.println(msg);
     
    }
  


}
