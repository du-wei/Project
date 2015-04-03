package com.webapp.junit.junit;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

@RunWith(Theories.class)
public class JunitTestRule {
	
	@Rule
	public MyRule myRule = new MyRule();
	
	@DataPoints
    public static int[] ages = {10, -2, 12};
	
	@Theory
	@Test
	public void hello(int age){
		Assume.assumeTrue(age > 3);
		System.out.println(age);
	}
	
}

class MyStatement extends Statement {
	private final Statement base;
	public MyStatement(Statement base){
		this.base = base;
	}
	@Override
    public void evaluate() throws Throwable {
	    System.out.println("begin");
		base.evaluate();
		System.out.println("end");
    }
}

class MyRule implements MethodRule {
	@Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
	    return new MyStatement(base);
    }
}
