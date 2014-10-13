package com.webapp.utils.string;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
* @ClassName: Utils.java
* @Package com.webapp.utils.string
* @Description: 字符串相关便捷工具类
* @author  king king
* @date 2014年10月13日 下午9:43:51
* @version V1.0
*/
public class Utils {
	//mina
	public static final String Dot = ",";
	/** EmailPattern,值为 {@value} */
	public static final String EmailPattern = "^(\\w+[-+.]*)+@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	/** PhonePattern,值为 {@value} */
	public static final String PhonePattern = "^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$";



	@Test
	public void testName() throws Exception {
//		System.out.println(Utils.safedPhone(null));
	}

	/**
	 * For example
     * <pre>example@163.com -> http://mail.163.com</pre>
	 * @param email
	 * @return Email home page link
	 */
	public static String toEmail(String email) {
		return StringUtils.isEmpty(email) ? null :
			email.contains("@gmail") ? "https://accounts.google.com" : "http://mail."+email.split("@")[1];
	}

	/**
	 * Verify the name
	 * @param name
	 * 		matches {@link #EmailPattern}
	 * @return boolean
	 */
	public static boolean validName(String name, int least, int most){
		return StringUtils.isNotEmpty(name) && name.matches("^[a-zA-Z0-9\\.\\-_]{4,20}$");
	}

	/**
	 * Verify the email
	 * @param email
	 * 		matches {@link #EmailPattern}
	 * @return boolean
	 */
	public static boolean validEmail(String email){
		return StringUtils.isNotEmpty(email) && email.matches(EmailPattern);
	}

	/**
	 * Verify the mobile phone number
	 * @param phone
	 * 		matches {@link #PhonePattern}
	 * @return boolean
	 */
	public static boolean validPhone(String phone){
		return StringUtils.isNotEmpty(phone) && phone.matches(PhonePattern);
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
	 * @param len The length of *
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
		return split(list, Dot);
	}

	/**
	 * For example
	 * <pre>["a", "b", "c"] -> a,b,c</pre>
	 * @param list
	 * @param split The separator default ,
	 * @return string
	 */
	public static <T> String split(List<T> list, String split) {
		StringBuffer result = new StringBuffer();
		list.forEach((item) -> result.append(item + Dot));
		return StringUtils.removeEnd(result.toString(), Dot);
	}

	/**
	 * For example
	 * <pre>a,b,c, -> a,b,c</pre>
	 * @param str
	 * @return
	 */
	public static String delTail(String str){
		return delTail(str, Dot);
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
