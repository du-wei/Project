package com.webapp.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** 日志切面 */
@Component
@Aspect
public class LogAspectJ {
	// 取得日志记录器Logger
	private static final Logger logger = LoggerFactory.getLogger(LogAspectJ.class);
	
	/** 使用@Pointcut注解定义一个切入点,切入点的名字为anyMethod(), 切入点正则表达式execution(*
	 * test.spring.action.UserAction.*(..))
	 * 的意思是拦截test.spring.action.UserAction类中的所有方法, 不论方法参数有无,也不管返回结果为何类型。 */
	@Pointcut("execution(* com.iphone.action.*(..))")
	@SuppressWarnings("unused")
	private void anyMethod() {
	}// 定义切入点的名字

	@Before("anyMethod()")
	// 定义前置通知
	public void myBeforeAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
		        + "类的" + joinpoint.getSignature().getName();
		logger.info("前置通知:" + classAndMethod + "方法开始执行！");
	}

	@AfterReturning("anyMethod()")
	// 定义后置通知
	public void myAfterReturningAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
		        + "类的" + joinpoint.getSignature().getName();
		logger.info("后置通知:" + classAndMethod + "方法执行正常结束！");
	}

	@AfterThrowing(pointcut = "anyMethod()", throwing = "e")
	// 定义异常通知
	public void myAfterThrowingAdvice(JoinPoint joinpoint, Exception e) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
		        + "类的" + joinpoint.getSignature().getName();
		logger.info("异常通知:" + classAndMethod + "方法抛出异常：" + e.getMessage());
	}

	@After("anyMethod()")
	// 定义最终通知
	public void myAfterAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
		        + "类的" + joinpoint.getSignature().getName();
		logger.info("最终通知:" + classAndMethod + "方法执行结束！");
	}

	@Around("anyMethod()")
	// 定义环绕通知
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long begintime = System.currentTimeMillis();// 记下开始时间
		// 传递给连接点对象进行接力处理
		Object result = pjp.proceed();
		long endtime = System.currentTimeMillis();// 记下结束时间
		String classAndMethod = pjp.getTarget().getClass().getName() + "类的"
		        + pjp.getSignature().getName();
		logger.info("环绕通知:" + classAndMethod + "方法执行结束,耗时"
		        + (endtime - begintime) + "毫秒！");
		return result;
	}
}
