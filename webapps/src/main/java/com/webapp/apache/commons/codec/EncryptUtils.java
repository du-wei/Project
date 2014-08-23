package com.webapp.apache.commons.codec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.webapp.nio.EncodeUtils;

public class EncryptUtils {

	private static String charset = "utf-8";

	@Test
	public void testName() throws Exception {
		Hex hex = new Hex();
		String str = "中文";
		char[] enbytes = null;
		String encodeStr = null;
		byte[] debytes = null;
		String decodeStr = null;

		enbytes = hex.encodeHex(str.getBytes());
		encodeStr = new String(enbytes);
		debytes = hex.decodeHex(enbytes);
		decodeStr = new String(debytes);

		System.out.println(EncryptUtils.encrypt("king"));
		System.out.println(EncryptUtils.decrypt(EncryptUtils.encrypt("king")));
	}

	public static String encrypt(String data) {
		return Base64.encodeBase64String(data.getBytes());
	}

	public static String decrypt(String data) {
		return EncodeUtils.decode(Base64.decodeBase64(data));
	}

	public static String decrypt(byte[] data) {
		return EncodeUtils.decode(Base64.decodeBase64(data));
	}

}
