package com.webapp.utils.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class Utils {

	@Test
	public void testName() throws Exception {
		System.out.println(Utils.delTail(".,"));
	}



	/**
	 * For example
     * <pre>example@163.com -> exam***@163.com</pre>
	 * @param email
	 * @return The safety of Email
	 */
	public static String safedEmail(String email) {
		return safedEmail(email, 3);
	}

	/**
	 * For example
	 * <pre>example@163.com -> exam***@163.com</pre>
	 * @param email
	 * @param len * the length of the
	 * @return The safety of Email
	 */
	public static String safedEmail(String email, int len) {
		return email.replaceAll("(.{" + len + "})(?=@)", "***");
	}

	/**
	 * For example
	 * <pre>13888888888 -> 138****8888</pre>
	 * @param phone
	 * @return The safety of phone
	 */
	public static String safedPhone(String phone) {
		return phone.replaceAll("(?<=\\d{3})(.{4})(?=\\d{4})", "****");
	}

	/**
	 * For example
	 * <pre>["a", "b", "c"] -> a,b,c</pre>
	 * @param list
	 * @return string
	 */
	public static <T> String split(List<T> list) {
		return split(list, ",");
	}

	/**
	 * For example
	 * <pre>["a", "b", "c"] -> a,b,c</pre>
	 * @param list
	 * @param split The separator
	 * @return string
	 */
	public static <T> String split(List<T> list, String split) {
		StringBuffer result = new StringBuffer();
		list.forEach((item) -> result.append(item + ","));
		return StringUtils.removeEnd(result.toString(), ",");
	}

	/**
	 * For example
	 * <pre>a,b,c, -> a,b,c</pre>
	 * @param str
	 * @return
	 */
	public static String delTail(String str){
		return delTail(str, ",");
	}

	/**
	 * For example
	 * <pre>a,b,c, -> a,b,c</pre>
	 * @param str
	 * @param remove Remove the character By default ,
	 * @return
	 */
	public static String delTail(String str, String remove){
		return StringUtils.removeEnd(str, remove);
	}
}
