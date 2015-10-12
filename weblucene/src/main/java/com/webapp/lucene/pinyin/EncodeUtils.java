package com.webapp.lucene.pinyin;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: EncodeUtils.java
 * @Package com.webapp.nio
 * @Description: TODO 类型描述
 * @author king chenglong
 * @date 2013-1-16 上午11:32:57
 * @version V1.0
 */
public class EncodeUtils {

	/* --------------------- decode ---------------------- */

	// ByteBuffer to String by charsetName
	public static String decode(ByteBuffer buffer, String charsetName) { // root
		return Charset.forName(charsetName)
				.decode((ByteBuffer) buffer.rewind()).toString();
	}

	public static String decode(ByteBuffer buffer) {
		return decode(buffer, "UTF-8");
	}

	// String to String by charsetName
	public static String decode(String str) {
		return decode(str, "UTF-8");
	}

	public static String decode(String str, String charsetName) {
		return decode(ByteBuffer.wrap(str.getBytes()), charsetName);
	}

	// CharBuffer to String by charsetName
	public static String decode(CharBuffer buffer) {
		return decode(buffer, "UTF-8");
	}

	public static String decode(CharBuffer buffer, String charsetName) {
		return decode(buffer.toString());
	}

	// CharBuffer to String by charsetName
	public static String decode(byte[] byteArray) {
		return decode(ByteBuffer.wrap(byteArray), "UTF-8");
	}

	public static String decode(byte[] byteArray, String charsetName) {
		return decode(ByteBuffer.wrap(byteArray), charsetName);
	}

	/* --------------------- encode ---------------------- */

	// String to ByteBuffer by charsetName
	public static ByteBuffer encode(String str) {
		return encode(str, "UTF-8");
	}

	public static ByteBuffer encode(String str, String charsetName) {
		return Charset.forName(charsetName).encode(str);
	}

	/**
	 * CharBuffer to ByteBuffer by UTF-8 encode
	 * 
	 * @param buffer
	 *            CharBuffer type
	 * @return ByteBuffer of by UTF-8 encode
	 */
	public static ByteBuffer encode(CharBuffer buffer) {
		return encode(buffer, "UTF-8");
	}

	/**
	 * CharBuffer to ByteBuffer by charsetName encode
	 * 
	 * @param buffer
	 *            CharBuffer type
	 * @param charsetName
	 *            encode name
	 * @return ByteBuffer of by charsetName encode
	 */
	public static ByteBuffer encode(CharBuffer buffer, String charsetName) {
		return Charset.forName(charsetName)
				.encode((CharBuffer) buffer.rewind());
	}

	/**
	 * byte[] to ByteBuffer by UTF-8 encode
	 * 
	 * @param byteArray
	 *            byte[] type
	 * @param charsetName
	 *            encode name
	 * @return ByteBuffer of by UTF-8 encode
	 */
	public static ByteBuffer encode(byte[] byteArray) {
		return ByteBuffer.wrap(byteArray);
	}

	public static byte[] encodeByte(ByteBuffer buffer) {
		return decode(buffer).getBytes();
	}

	public static Charset getUTFCharset() {
		return StandardCharsets.UTF_8;
	}

	public static Charset getDefaultCharset() {
		return Charset.defaultCharset();
	}

	/** Prevent instantiation */
	private EncodeUtils() {
	}

}
