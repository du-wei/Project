package com.webapp.apache.commons.codec;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

public class EncryptJavaUtils {

	static String charset = "utf-8";

	// @Test
	// public void testName() throws Exception {
	// System.out.println(EncryptJavaUtils.encryptBASE64("king"));
	// System.out.println(EncryptJavaUtils.decryptBASE64(EncryptJavaUtils.encryptBASE64("king")));
	//
	//
	// System.out.println(EncryptJavaUtils.encryptMD5("king"));
	// System.out.println(EncryptJavaUtils.encryptSHA("king"));
	//
	// System.out.println(EncryptJavaUtils.initMacKey());
	// System.out.println(EncryptJavaUtils.encryptHMAC("king".getBytes(),
	// EncryptJavaUtils.initMacKey()));
	// }

	// public static String encryptBASE64(String data) throws Exception{
	// return new BASE64Encoder().encodeBuffer(data.getBytes());
	// }
	//
	// public static String decryptBASE64(String data) throws Exception{
	// return new String(new BASE64Decoder().decodeBuffer(data));
	// }
	//
	//
	// public static String encryptMD5(String data) throws Exception{
	// MessageDigest md5 = MessageDigest.getInstance("MD5");
	// md5.update(data.getBytes());
	// return Base64.encode(md5.digest());
	// }
	//
	// public static String encryptSHA(String data) throws Exception{
	// MessageDigest md5 = MessageDigest.getInstance("SHA");
	// md5.update(data.getBytes());
	// return Base64.encode(md5.digest());
	// }

	// public static String initMacKey() throws Exception {
	// KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
	//
	// SecretKey secretKey = keyGenerator.generateKey();
	// return EncryptJavaUtils.encryptBASE64(new
	// String(secretKey.getEncoded()));
	// }
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(data, "HmacMD5");
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);

	}

}
