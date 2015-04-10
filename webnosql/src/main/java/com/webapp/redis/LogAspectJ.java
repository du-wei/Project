package com.webapp.redis;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/** 日志切面 */
@Component
@Aspect
public class LogAspectJ {//redis.clients.jedis.Jedis
	@Before("execution(* redis.clients.jedis..*(..))")
	public void myBeforeAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "类的" + joinpoint.getSignature().getName();
		System.out.println("前置通知:" + classAndMethod + "方法开始执行！");
	}
	
	@Around("execution(* com.webapp.redis..*(..))")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long begintime = System.currentTimeMillis();// 记下开始时间
		// 传递给连接点对象进行接力处理
		Object result = pjp.proceed();
		long endtime = System.currentTimeMillis();// 记下结束时间
		String classAndMethod = pjp.getTarget().getClass().getName() + "类的"
				+ pjp.getSignature().getName();
		System.out.println("+++++++++++++++=");
		return result;
	}
}
