package com.webapp.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {
	private RegexUtils(){}

	public static String match(String data, String regex) {
	    Matcher matcher = Pattern.compile(regex).matcher(data);
	    return matcher.find() ? matcher.group() : "";
    }

	public static boolean isMatch(String data, String regex) {
	    Matcher matcher = Pattern.compile(regex).matcher(data);
	    return matcher.matches();
    }

}
