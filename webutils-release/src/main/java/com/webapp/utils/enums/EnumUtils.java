package com.webapp.utils.enums;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
* @ClassName: EnumUtils.java
* @Package com.webapp.utils.enums
* @Description: enum utils
* @author  king king
* @date 2014年10月21日 下午8:34:41
* @version V1.0
*/
public class EnumUtils {

	/**
     * @Description Convert enum attribute to list
     * @param clz enum class
     * @param prop enum attribute
	 * @return list
	 */
	public static <E extends Enum<E>> List<String> getList(Class<E> clz, String prop){
		Method method = getMethod(clz, get(prop));
		if(method == null) return null;

		List<String> result = new ArrayList<String>();
		Iterator<E> iterator = EnumSet.allOf(clz).iterator();
		while(iterator.hasNext()){
			E next = iterator.next();
			result.add(invoke(method, next).toString());
		}
		return result;
	}

	/**
     * @Description Get enum attribute value
     * @param clz enum class
     * @param prop enum attribute
	 * @return string value
	 */
	public static <E extends Enum<E>> String valueOf(E enumEle, String prop){
		Method method = getMethod(enumEle.getClass(), get(prop));
		if(method == null) return null;
		return invoke(method, enumEle).toString();
	}

	/**
     * @param clz enum class
     * @param prop enum attribute
     * @param prop enum value
	 * @return boolean
	 */
	public static <E extends Enum<E>> boolean isNotExist(Class<E> clz, String prop, String value){
		return !isExist(clz, prop, value);
	}

	/**
     * @param clz enum class
     * @param prop enum attribute
     * @param prop enum value
	 * @return boolean
	 */
	public static <E extends Enum<E>> boolean isExist(Class<E> clz, String prop, String value){
		return getEnum(clz, prop, value) != null ? true : false;
	}

	/**
	 * @Description Get enum by attribute and value
     * @param clz enum class
     * @param prop enum attribute
     * @param prop enum value
	 * @return boolean
	 */
	public static <E extends Enum<E>> E getEnum(Class<E> clz, String prop, String value){
		Method method = getMethod(clz, get(prop));
		if(method == null) return null;

		Iterator<E> iterator = EnumSet.allOf(clz).iterator();
		while(iterator.hasNext()){
			E next = iterator.next();
			if(invoke(method, next).equals(value)){
				return next;
			}
		}
		return null;
	}

	private static <E> Object invoke(Method method, E obj) {
		Object result = null;
		try {
	        result = method.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	        e.printStackTrace();
        }
		return result;
    }

	private static String get(String prop) {
		return "get" + StringUtils.capitalize(prop);
    }


//	private static boolean hasField(Class<?> clz, String prop){
//		return getField(clz, prop) != null ? true : false;
//	}
//
//	private static Field getField(Class<?> clz, String prop){
//		Field field = null;
//		try {
//	        field = clz.getField(prop);
//        } catch (NoSuchFieldException | SecurityException e) {
//	        e.printStackTrace();
//        }
//		return field;
//	}

	private static <T> Method getMethod(Class<T> clz, String prop, Class<?>... parameterTypes){
		try {
	        return clz.getMethod(prop, parameterTypes);
        } catch (NoSuchMethodException | SecurityException e) {
	        e.printStackTrace();
	        return null;
        }
	}

}
