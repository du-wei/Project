package com.webapp.utils.enums;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import com.webapp.utils.clz.ClzUtils;

/**
* @ClassName: EnumUtils.java
* @Package com.webapp.utils.enums
* @Description: enum utils
* @author  king king
* @date 2014年10月21日 下午8:34:41
* @version V1.0
*/
public final class EnumUtils {

	private EnumUtils(){}

	@SafeVarargs
    public static <E extends Enum<E>> List<String> getList(Class<E> clz, String prop, E...excludes){
		Field field = ClzUtils.getField(clz, prop);

		List<String> result = new ArrayList<String>();
		if(field == null) return result;

		EnumSet<E> allOf = EnumSet.allOf(clz);
		if(excludes != null) allOf.removeAll(Arrays.asList(excludes));

		Iterator<E> iterator = allOf.iterator();
		while(iterator.hasNext()){
			E next = iterator.next();
			result.add(ClzUtils.getFieldVal(field, next).toString());
		}
		return result;
	}

    @SafeVarargs
    @SuppressWarnings("unchecked")
	public static <E extends Enum<E>, T> List<T> getList(Class<E> clz, String prop, Class<T> returnType, E...excludes){
		Field field = ClzUtils.getField(clz, prop);

		List<T> result = new ArrayList<T>();
		if(field == null) return result;

		EnumSet<E> allOf = EnumSet.allOf(clz);
		if(excludes != null) allOf.removeAll(Arrays.asList(excludes));

		Iterator<E> iterator = allOf.iterator();
		while(iterator.hasNext()){
			E next = iterator.next();
			result.add((T)ClzUtils.getFieldVal(field, next));
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
		Field field = ClzUtils.getField(enumEle.getClass(), prop);
		if(field == null) return null;

		return ClzUtils.getFieldVal(field, enumEle).toString();
	}

	/**
     * @param clz enum class
     * @param prop enum attribute
     * @param prop enum value
	 * @return boolean
	 */
	public static <E extends Enum<E>> boolean isNotExist(Class<E> clz, String prop, Object value){
		return !isExist(clz, prop, value);
	}

	/**
     * @param clz enum class
     * @param prop enum attribute
     * @param prop enum value
	 * @return boolean
	 */
	public static <E extends Enum<E>> boolean isExist(Class<E> clz, String prop, Object value){
		return getEnum(clz, prop, value) != null ? true : false;
	}

	/**
	 * @Description Get enum name
     * @param clz enum class
     * @param prop enum name
	 * @return boolean
	 */
	public static <E extends Enum<E>> E getEnum(Class<E> clz, String name){

		Iterator<E> iterator = EnumSet.allOf(clz).iterator();
		while(iterator.hasNext()){
			E next = iterator.next();
			if(next.name().equals(name)){
				return next;
			}
		}
		return null;
	}

	/**
	 * @Description Get enum by attribute and value
     * @param clz enum class
     * @param prop enum attribute
     * @param prop enum value
	 * @return boolean
	 */
	public static <E extends Enum<E>> E getEnum(Class<E> clz, String prop, Object value){
		Field field = ClzUtils.getField(clz, prop);
		if(field == null) return null;

		Iterator<E> iterator = EnumSet.allOf(clz).iterator();
		while(iterator.hasNext()){
			E next = iterator.next();
			if(ClzUtils.getFieldVal(field, next).equals(value)){
				return next;
			}
		}
		return null;
	}

	@SafeVarargs
    public static <E extends Enum<E>> List<E> getEnums(E[] all, E... excludes) {
    	List<E> result = new ArrayList<E>(Arrays.asList(all));
		result.removeAll(Arrays.asList(excludes));

		return result;
    }

}
