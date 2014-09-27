package com.webapp.utils.test;

public class Console {

	public static void print(String val){
		System.out.print(val);
	}
	public static void println(String val){
		System.out.print(val);
	}

	public static void out(String format, Object... args){
		System.out.println(String.format(format, args));
	}

}
