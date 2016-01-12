package com.webapp.utils.db;

import java.lang.reflect.Field;

import com.webapp.utils.string.Utils;
import com.webapp.utils.string.Utils.Symbol;

public final class MybatisUtils {

	private MybatisUtils(){}

	public static <T> String setModel(Class<T> clz) {
		StringBuffer columns = new StringBuffer();
		String name = clz.getSimpleName();
		String camel = Utils.toCamel(name);
		columns.append(name + " " + camel + " = new " + name + "();\n");
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            columns.append(camel + ".set" + Utils.toPascal(col) + "();\n");
        }
        return Utils.delTail(columns.toString());
	}

    public static <T> String sql_insert_cols(Class<T> clz) {
    	 return sql_insert(clz, false);
    }

    public static <T> String sql_insert_vals(Class<T> clz) {
        return sql_insert(clz, true);
    }

    private static <T> String sql_insert(Class<T> clz, boolean isProp) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            if(isProp){
                columns.append("#{" + col + "},");
            }else {
                columns.append(Utils.toSnake(col) + ',');
            }
        }
        return Utils.delTail(columns.toString());
    }

    public static <T> String sql_select_cols(Class<T> clz) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            String consts = Utils.toSnake(col);
            columns.append(consts + (consts.contains(Symbol.Underline) ? " " + col : "") + ",");
        }
        return Utils.delTail(columns.toString());
    }

    public static <T> String sql_update_cols(Class<T> clz) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            String consts = Utils.toSnake(col);
            columns.append(consts + "=#{" + col + "},");
        }
        return Utils.delTail(columns.toString());
    }

    public static <T> String const_cols(Class<T> clz){
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            columns.append("public final static String ");

            String consts = Utils.toSnake(col);
            columns.append(consts.toUpperCase() + " = \"" + consts.toLowerCase() + "\";\n");
        }
        return columns.toString();
    }
}
