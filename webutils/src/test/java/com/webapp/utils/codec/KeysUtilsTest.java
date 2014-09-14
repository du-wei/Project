package com.webapp.utils.codec;

import org.junit.Test;

public class KeysUtilsTest {

	private static String hello = "hello";

	@Test
	public void encrypt() throws Exception{
		System.out.println(KeysUtils.encode(hello));
	}

	@Test
	public void decrypt() throws Exception{
		System.out.println(KeysUtils.decode(KeysUtils.encode(hello)));
	}

	@Test
	public void shaHex() throws Exception{
		System.out.println(KeysUtils.shaHex(hello));
	}

	@Test
	public void md5Hex() throws Exception{
		System.out.println(KeysUtils.md5Hex(hello));
	}

}
