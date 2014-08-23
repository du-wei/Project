package com.webapp.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class Regular {
	/*
	 * 开头 代表(.{3})的一部分 即第一位 结尾 代表(.{3})的一部分 即最后位 前面 代表(.{3})前面的一部分 即(.{3})之前的一位
	 * 后面 代表(.{3})后面的一部分 即(.{3})之后的一位 1 2 (.{3})(?:g) 相当于()匹配()中的内容 但是不捕获?所在的组
	 * 
	 * (.{3})(?=g) 相当于组(.{3})的后面必须是g，并且g不算在group(0)里 (?=g)(.{3})
	 * 相当于组(.{3})的开头必须是g，并且算上g一共为3位 ?=a equals ^a kingking
	 * (?=k)king(?=k)kin(?=g) 并且g不算在group(0)里
	 * 
	 * (.{3})(?!g) 相当于组(.{3})的后面一定不是g，并且g不算在group(0)里 (?!g)(.{3})
	 * 相当于组(.{3})的开头一定不是g，并且g不算在group(0)里 (?!x)所在的位置不能为x 并且x不算在group(0)里
	 * 
	 * (.{3})(?<=g) 相当于组(.{3})的结尾必须是g，并且算上g一共为3位 (?<=g)(.{3})
	 * 相当于组(.{3})的前面必须是g，并且g不算在group(0)里
	 * 
	 * (.{3})(?<!g) 相当于组(.{3})的结尾一定不是g，并且g不算在group(0)里 (?<!g)(.{3})
	 * 相当于组(.{3})的前面一定不是g，并且g不算在group(0)里
	 * 
	 * ?<= ?<! 使用 从尾开始看(?<=在后) (?<=在前)开头的不算
	 * 
	 * ?: ?= ?! ?<= ?<! 组不被捕获，也就是没有group(2)，
	 * 
	 * (?i) (?i:java)
	 */
	public void p(Object o) {
		System.out.println(o);
	}

	@Test
	public void okdddd() {
		String str = "kingking";
		Matcher m = Pattern.compile("king(?!k)king(?!g)").matcher(str);
		if (m.find()) {
			System.out.println(m.group());
		}

	}

	public static void main(String[] args) {

		boolean bool = Pattern.matches(".[^a]ello", "hello");
		System.out.println(bool);

	}

}
