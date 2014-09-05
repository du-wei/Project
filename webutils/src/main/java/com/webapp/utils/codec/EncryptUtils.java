package com.webapp.utils.codec;

import org.apache.commons.codec.binary.Base64;

import com.webapp.utils.file.EncodeUtils;

public class EncryptUtils {

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
