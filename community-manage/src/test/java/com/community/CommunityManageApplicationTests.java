package com.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.community.util.redis.RedisUtil;


@SpringBootTest
class CommunityManageApplicationTests {

	 @Autowired
	 RedisUtil redisUtil;
	 /**
     * redis的操作
     *
     */
    @Test
    public void test01(){
    	System.out.println("测试redis");
    	redisUtil.set("hello", "king",10);
    	
	    String msg = (String)redisUtil.get("hello");

	    System.out.println(msg);
     
    }
  


}
