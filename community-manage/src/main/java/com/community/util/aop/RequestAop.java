package com.community.util.aop;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.community.entity.common.User;
import com.community.util.Assert;
import com.community.util.UserContext;
import com.community.util.redis.RedisUtil;

/**
 * 自定义拦截器
 */
@Component
@Aspect
public class RequestAop {

	/**
	 * 排除拦截的url列表
	 */
	static final List<String> LOGIN_URL = new ArrayList<String>(){
		private static final long serialVersionUID = 1L;
	{
		add("/doLogin");				
		add("/register");
		add("/sms/sendCode");
		add("/upload/images");
		add("/getCode");
		add("/getOpenId");
		add("/wxpay");
	}};
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * execution表达式第一个*表示匹配任意的方法返回值，..(两个点)表示零个或多个，
	 * 第一个..表示module包及其子包,第二个*表示所有类, 
	 * 第三个*表示所有方法，第二个..表示方法的任意参数个数
	 */
	@Pointcut("execution(* com.community..*.*Controller.*(..))")
	public void init() {
		
	}
	
	@Before("init()")
	public void beforeAdvice(JoinPoint joinPoint) {
		// 进入方法前拦截
	}
	
 /**
  * 增强环绕
  * 
  * @param joinPoint
  * @return
  * @throws Throwable
  */
    @Around("init()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String url = request.getRequestURI();  
		//需要登录才能访问
		if(!LOGIN_URL.contains(url)) {
			String token = request.getHeader("token");
			if(!"token".equals(token)) {
				Assert.notEmpty(token, "token不能为空");
				User user = (User) redisUtil.get(token);
				Assert.notNull(user, "token已过期");
				
				UserContext.set(user);
			}
		}

		Object obj = joinPoint.proceed();
		UserContext.remove();
		return obj;
        
    }
    
    @After("init()")
	public void afterAdvice(JoinPoint joinPoint) {
    	
	}
}
