package com.webapp.utils.test;

import java.lang.reflect.Field;

import com.webapp.utils.string.Utils;
import com.webapp.utils.string.Utils.Symbol;

public class CamelUtils {

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
                columns.append(Utils.toUnderline(col) + ',');
            }
        }
        return Utils.delTail(columns.toString());
    }

    public static <T> String getSelPropOrCols(Class<T> clz) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();

            String consts = Utils.toUnderline(col);

            columns.append(consts + (consts.contains(Symbol.Underline) ? " " : "") + col + ",");
        }
        return Utils.delTail(columns.toString());
    }

    public static <T> String getFiledConst(Class<T> clz){
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            columns.append("public static final String ");

            String consts = Utils.toUnderline(col);
            columns.append(consts.toUpperCase() + " = \"" + consts.toLowerCase() + "\";\n");
        }
        return columns.toString();
    }
}
