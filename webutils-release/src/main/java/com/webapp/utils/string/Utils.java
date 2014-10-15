package com.webapp.utils.string;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.webapp.utils.jpinyin.PinyinHelper;
import com.webapp.utils.regex.RegexConst;

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
	public static final String Dot = ".";
	public static final String Lparen = "(";
	public static final String Rparen = ")";
	public static final String Lbrace = "{";
	public static final String Rbrace = "}";
	public static final String Lbracket = "[";
	public static final String Rbracket = "]";
	public static final String Comma = ",";
	public static final String Colon = ":";
	public static final String Underline = "_";

	/**
	 * pinyin
     * <pre>中国 -> zhongguo</pre>
	 * @param str
	 * @return pinyin
	 */
	public static String toPinyin(String str) {
	    return PinyinHelper.convertToPinyinString(str, "");
    }

	/**
	 * pinyin
     * <pre>中国 -> zhongguo</pre>
	 * @param str
	 * 		separator pinyin separator
	 * @return pinyin
	 */
	public static String toPinyin(String str, String separator) {
	    return PinyinHelper.convertToPinyinString(str, separator);
    }

	/**
	 * ShortPinyin
     * <pre>中国 -> zg</pre>
	 * @param str
	 * @return ShortPinyin
	 */
	public static String toShortPinyin(String str) {
	    return PinyinHelper.getShortPinyin(str);
    }

	/**
	 * Underline
     * <pre>userId -> user_id</pre>
	 * @param str
	 * @return Underline
	 */
	public static String toUnderline(String str) {
		if (str == null) return null;

		StringBuilder sb = new StringBuilder();
		boolean prevUpper = false, curUpper = false, nextUpper = false;
		for (int i = 0; i < str.length(); i++) {
			char s = str.charAt(i);
			prevUpper = curUpper;
			curUpper = (i == 0) ? Character.isUpperCase(s) : nextUpper;
			nextUpper = (i < str.length() - 1 ? Character.isUpperCase(str.charAt(i + 1)) : true);

			if(i > 0 && curUpper && !(nextUpper && prevUpper)) sb.append(Underline);

			sb.append(Character.toLowerCase(s));
		}
		return sb.toString();
	}

	/**
	 * Camel-Case
     * <pre>user_id -> userId</pre>
	 * @param str
	 * @return Camel-Case
	 */
    public static String toCamel(String str) {
    	if (str == null) return null;

    	if(str.contains(Underline)) str = str.toLowerCase();
        StringBuilder sb = new StringBuilder(str.length());
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(String.valueOf(c) == Underline){
            	upperCase = true;
            }else {
				if(upperCase){
					sb.append(Character.toUpperCase(c));
	                upperCase = false;
				}else {
					 sb.append(c);
				}
			}
        }
        return sb.toString();
    }

    /**
	 * Pascal Case == Upper Camel Case
     * <pre>userId -> UserId</pre>
	 * @param str
	 * @return Pascal Case
	 */
    public static String toPascal(String str) {
    	if (str == null) return null;
    	str = toCamel(str);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
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
	 * @return boolean
	 */
	public static boolean validName(String name, int least, int most){
		return StringUtils.isNotEmpty(name) && name.matches("^[a-zA-Z0-9\\.\\-_]{4,20}$");
	}

	/**
	 * Verify the email
	 * @param email
	 * 		matches {@link com.webapp.utils.regex.RegexConst#Email }
	 * @return boolean
	 */
	public static boolean regexEmail(String email){
		return StringUtils.isNotEmpty(email) && email.matches(RegexConst.Email);
	}

	/**
	 * Verify the mobile phone number
	 * @param phone
	 * 		matches {@link com.webapp.utils.regex.RegexConst#Mobile}
	 * @return boolean
	 */
	public static boolean regexMobile(String phone){
		return StringUtils.isNotEmpty(phone) && phone.matches(RegexConst.Mobile);
	}

	/**
	 * Verify the identity card
	 * @param idcard
	 * 		matches {@link com.webapp.utils.regex.RegexConst#Idcard}
	 * @return boolean
	 */
	public static boolean regexIdcard(String idcard){
		return StringUtils.isNotEmpty(idcard) && idcard.matches(RegexConst.Idcard);
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
		return split(list, Comma);
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

	/**
	 * Is Chinese
	 * @param str
	 * @return boolean
	 */
	public static boolean isChinese(String str){
		return isChinese(str, false);
	}

	/**
	 * Is Chinese
	 * @param str
	 * @return boolean
	 */
	public static boolean isChinese(String str, boolean hasSymbols) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c, hasSymbols)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isChinese(char c, boolean hasSymbols) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
            return true;
        }
        if (hasSymbols) {
            if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                return true;
            }
        }
        return false;
    }

}