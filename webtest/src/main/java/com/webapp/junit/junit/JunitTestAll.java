package com.webapp.junit.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)						//指定运行器
@SuiteClasses({ JunitTestPara.class })//指明需要测试的类
public class JunitTestAll {
	public static void main(String[] args) {
//	    Suite.class
    }
}
