package com.webapp.utils.codec;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class KeysUtils {

	public static void main(String[] args) {
	    System.out.println();
    }

	/**
	 * <p>Encrypt the data</p>
	 * @param data
	 * 			data to encrypt
	 * @return Encrypted string
	 */
	public static String encode(String data) {
		return Base64.encodeBase64String(data.getBytes());
	}

	/**
	 * <p>Decrypt the data</p>
	 * @param data
	 * @return Decrypt string
	 */
	public static String decode(String data) {
		return decode(data.getBytes());
	}

	/**
	 * <p>Decrypt the data</p>
	 * @param data
	 * @return Decrypt string
	 */
	public static String decode(byte[] data) {
		return StringUtils.toEncodedString(Base64.decodeBase64(data), Charset.forName("utf-8"));
	}

	/**
	 * <p>shaHex the data</p>
	 * <p>From DigestUtils.shaHex(data)</p>
	 * @param data
	 * @return shaHex string
	 */
	public static String shaHex(String data){
		return DigestUtils.shaHex(data).toUpperCase();
	}

	/**
	 * <p>md5Hex the data</p>
	 * <p>From DigestUtils.md5Hex(data)</p>
	 * @param data
	 * @return md5Hex string
	 */
	public static String md5Hex(String data){
		return DigestUtils.md5Hex(data).toUpperCase();
	}

}
