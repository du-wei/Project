package com.webapp.utils.db;

import java.lang.reflect.Field;

import com.webapp.utils.string.Utils;
import com.webapp.utils.string.Utils.Symbol;

public final class MybatisUtils {

	private MybatisUtils(){}

	public static <T> void propSet(Class<T> clz) {
		StringBuffer columns = new StringBuffer();
		String name = clz.getSimpleName();
		String camel = Utils.toCamel(name);
		columns.append(name + " " + camel + " = new " + name + "();\n");
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            columns.append(camel + ".set" + Utils.toPascal(col) + "();\n");
        }
        String result = Utils.delTail(columns.toString());
        System.out.println(result);
	}

    public static <T> void insertCols(Class<T> clz) {
    	 sql_insert(clz, false);
    }

    public static <T> void insertVals(Class<T> clz) {
        sql_insert(clz, true);
    }

    private static <T> void sql_insert(Class<T> clz, boolean isProp) {
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
        String result = Utils.delTail(columns.toString());
        System.out.println(result);
    }

    public static <T> void searchCols(Class<T> clz) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            String consts = Utils.toSnake(col);
            columns.append(consts + (consts.contains(Symbol.LineUnder) ? " " + col : "") + ",");
        }
        String result = Utils.delTail(columns.toString());
        System.out.println(result);
    }

    public static <T> void updateCols(Class<T> clz) {
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            String consts = Utils.toSnake(col);
            columns.append(consts + "=#{" + col + "},");
        }
        String result = Utils.delTail(columns.toString());
        System.out.println(result);
    }

    public static <T> void propConst(Class<T> clz){
        StringBuffer columns = new StringBuffer();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields){
            String col = field.getName();
            columns.append("public final static String ");

            String consts = Utils.toSnake(col);
            columns.append(consts.toUpperCase() + " = \"" + consts.toLowerCase() + "\";\n");
        }
        String result = Utils.delTail(columns.toString());
        System.out.println(result);
    }
}
