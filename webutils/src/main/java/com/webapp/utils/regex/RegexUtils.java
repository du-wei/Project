package com.webapp.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	public static String match(String data, String regex) {
	    Matcher matcher = Pattern.compile(regex).matcher(data);
	    return matcher.group();
    }

}
