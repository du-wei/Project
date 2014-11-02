package com.webapp.utils.akka;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;




public class AkkaMain {

	//ehcache
	//http://haohaoxuexi.iteye.com/category/319453

	//(x&y)+((x^y)>>1) 平均值
	//(~x+1) 求相反数

	public static void main(String[] args) {
//		Main.main(new String[]{"com.webapp.utils.akka.MyActor"});
		int x = 1;
		int y = 7;
		x ^= y;
		y ^= x;
		x ^= y;
    }
}
