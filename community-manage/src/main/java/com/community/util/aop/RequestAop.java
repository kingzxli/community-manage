package com.community.util.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 自定义拦截器
 */
@Component
@Aspect
public class RequestAop {
	
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
    	 //-----------init()方法环绕增强-----------    
 
        
        // proceed()：执行被通知的方法，如不调用将会阻止被通知的方法的调用，也就导致Controller中的return会404
        return joinPoint.proceed();
    }
    
    @After("init()")
	public void afterAdvice(JoinPoint joinPoint) {
    	// 进入方法后执行
	}
}
