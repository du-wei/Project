package com.webapp.modules;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class DynamicPointcut extends DynamicMethodMatcherPointcut {

	@Override
    public ClassFilter getClassFilter() {
	    return super.getClassFilter();
    }

	@Override
    public boolean matches(Method method, Class<?> clz) {
		System.out.println("matcher2");
	    return super.matches(method, clz);
    }

	@Override
    public boolean matches(Method method, Class<?> clz, Object[] arg2) {
		System.out.println("matcher3");
	    return false;
    }

}
