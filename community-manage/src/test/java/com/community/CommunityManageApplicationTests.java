package com.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.community.exception.CustomException;
import com.community.util.LogUtils;
import com.community.util.redis.RedisUtil;


@SpringBootTest
class CommunityManageApplicationTests {
	
	Logger logger = LoggerFactory.getLogger(getClass());

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
    
    @Test
    public void test02(){
    	logger.trace("这是trace日志");
    	logger.debug("这是debug日志");
    	logger.info("这是info日志");
    	logger.warn("这是warn日志");
    	logger.error("这是error日志");
    	LogUtils.printLog("这是logutil的日志输出");
    	LogUtils.printException("这个logUtil的异常输出", new CustomException("这是一个错误"));
    }
  


}
