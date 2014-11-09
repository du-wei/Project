package com.webapp.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataSourceAspecj {

	@Around("execution(@com.webapp.datasource.DataSource * *(..))")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		Class<? extends ProceedingJoinPoint> dsClz = pjp.getClass();
		Method dsMethod = ((MethodSignature)pjp.getSignature()).getMethod();
		
		DataSource dsAnno = null;
		if(dsMethod.isAnnotationPresent(DataSource.class)){
			dsAnno = dsMethod.getAnnotation(DataSource.class);
		}else if (dsClz.isAnnotationPresent(DataSource.class)) {
			dsAnno = dsClz.getAnnotation(DataSource.class);
		}
		
		if(dsAnno != null && dsAnno.value().equals("")){
			MultiDataSource.setDataSourceKey(dsAnno.value());
		}
		
		Object result = pjp.proceed();
		
		MultiDataSource.setDefDataSource();
		return result;
	}
	

}
