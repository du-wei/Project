package com.webapp.modules;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExpAspectj {
	/*
	 * +++++++++++++++++++++++++++++++++++++++++++
	 * -->	* 	匹配任何数量字符
	 * -->	..	匹配任何数量字符的重复
	 * -->	+ 	匹配指定类型的子类型
	 * -->	只有支持所有通配符	execution() within()
	 * -->	只支持+,意义不大	args() this() target()
	 * -----------------------------------------------
	 * -->	&& and	
	 * -->	|| or 	
	 * -->	! not
	 * -----------------------------------------------
	 * -->	方法切点函数
	 * -->		execution(方法模式模式串)		
	 * -->		@annotation(方法注解类名)	
	 * -->	方法入参切点函数
	 * -->		args(类名)
	 * -->		@args(注解类名)
	 * -->	目标类切点函数
	 * -->		within(类名匹配串)
	 * -->		target(类名)
	 * -->		@within(类注解类名)
	 * -->		@target(类注解类名)
	 * -->	代理类切点函数
	 * -->		this(类名)
	 * +++++++++++++++++++++++++++++++++++++++++++
	 * -->	注解？ 类的全限定名字
	 * -->	注解？ 修饰符? 返回值类型  类型声明?方法名(参数列表) 异常列表？
	 * 
	 * -->	1
	 * -->	execution(public * *(..))
	 * -->	execution(* com.webapp.*(..))
	 * -->	execution(* com.webapp..*(..))
	 * -->	execution(* com..aspectj.*(..))
	 * -->	execution(* com..aspectj+.*(..))
	 * -->	execution(* (!com..aspectj+).*(..))
	 * -->	execution(@java.lang.Deprecated * *(..))注解中有Deprecated
	 * -->	execution((@java.lang.Deprecated *) *(..))返回值类型有Deprecated
	 * -->	execution(* (@java.lang.Deprecated *).*(..))方法中有Deprecated
	 * -->	@annotation(com.webapp.database.DataSource)
	 * 
	 * -->	2
	 * -->	args(java.lang.Integer)
	 * -->	@args()
	 * 
	 * -->	3
	 * -->	within(com.webapp.aspectj.AspectjMethod)
	 * -->	within(com.webapp.aspectj.AspectjMethod+)
	 * -->	within(com.webapp.aspectj.*)
	 * -->	within(com.webapp..*)
	 * -->	within(@com.webapp.database.DataSource *)
	 * -->	within(@com..DataSource *)
	 * -->	within(@*..DataSource *)
	 * -->	target()
	 * -->	@within()
	 * -->	@target()
	 * 
	 * -->	4
	 * -->	this()
	 * 
	 * bean(bean)
	 */
	
	// 定义切入点的名字 	@Pointcut()
	// 定义前置通知	@Before()
	//	BeforeAdvice
	// 定义后置通知	@AfterReturning()
	//	AfterReBeforeAdvice
	// 定义异常通知	@AfterThrowing()
	//	ThrowsAdvice
	// 定义环绕通知	@Around()
	//	MethodInterceptor
	// 定义最终通知	@After()
	
	
	
//	@Before("within(*..*Dao+) || within(*..*DAO+) || within(*..DataSourceSwitch+)")
	public void myAfterReturningAdvice(JoinPoint joinpoint) {
		
		System.out.println("AfterReturning");
		
		
	}
	
	
}
