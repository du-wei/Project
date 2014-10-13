package com.webapp.utils.test;

import java.lang.reflect.Field;

import com.webapp.utils.string.Utils;

public class CamelUtils {

	private static final String SEPARATOR = "_";

	public static String toUnderline(String str) {
		if (str == null) return null;

		StringBuilder sb = new StringBuilder();
		boolean prevUpper = false, curUpper = false, nextUpper = false;
		for (int i = 0; i < str.length(); i++) {
			char s = str.charAt(i);
			prevUpper = curUpper;
			curUpper = (i == 0) ? Character.isUpperCase(s) : nextUpper;
			nextUpper = (i < str.length() - 1 ? Character.isUpperCase(str.charAt(i + 1)) : true);

			if(i > 0 && curUpper && !(nextUpper && prevUpper)) sb.append(SEPARATOR);

			sb.append(Character.toLowerCase(s));
		}
		return sb.toString();
	}

    public static String toCamel(String str) {
    	if (str == null) return null;

    	if(str.contains(SEPARATOR)) str = str.toLowerCase();
        StringBuilder sb = new StringBuilder(str.length());
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(String.valueOf(c) == SEPARATOR){
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

    public static String toPascal(String str) {
    	if (str == null) return null;
    	str = toCamel(str);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

	//camel
	//pascal
    public static <T> String getInsertPropOrCols(Class<T> clz, boolean isProp) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            if(isProp){
                columns.append("#{" + col + "},");
            }else {
                columns.append(toUnderline(col) + ',');
            }
        }
        return Utils.delTail(columns.toString());
    }

    public static <T> String getSelPropOrCols(Class<T> clz) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();

            String consts = toUnderline(col);

            columns.append(consts + (consts.contains(SEPARATOR) ? " " : "") + col + ",");
        }
        return Utils.delTail(columns.toString());
    }

    public static <T> String getFiledConst(Class<T> clz){
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            columns.append("public static final String ");

            String consts = toUnderline(col);
            columns.append(consts.toUpperCase() + " = \"" + consts.toLowerCase() + "\";\n");
        }
        return columns.toString();
    }
}
