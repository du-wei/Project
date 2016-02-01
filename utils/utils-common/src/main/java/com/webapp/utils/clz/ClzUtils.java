package com.webapp.utils.clz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

public final class ClzUtils {

	public static boolean hasAnno(Class<?> clz, Class<? extends Annotation> anno) {
		return clz.isAnnotationPresent(anno);
    }

	public static boolean hasAnno(Method method, Class<? extends Annotation> anno) {
		return method.isAnnotationPresent(anno);
    }

	public static <T extends Annotation> T getAnno(Method method, Class<T> anno) {
		return method.getAnnotation(anno);
    }

	public static <T extends Annotation> T getAnno(Class<T> clz, Class<T> anno) {
		return clz.getAnnotation(anno);
    }

	public static <T extends Annotation> T getAnnoIfClz(Class<T> clz, Method method, Class<T> anno) {
		T tAnno = null;
		if(clz.isAnnotationPresent(anno)){
			tAnno = clz.getAnnotation(anno);
		}else if (method.isAnnotationPresent(anno)) {
			tAnno = method.getAnnotation(anno);
		}
		return tAnno;
    }

	public static <T extends Annotation> T getAnnoIfMethod(Class<T> clz, Method method, Class<T> anno) {
		T tAnno = null;
		if(method.isAnnotationPresent(anno)){
			tAnno = method.getAnnotation(anno);
		}else if (clz.isAnnotationPresent(anno)) {
			tAnno = clz.getAnnotation(anno);
		}
		return tAnno;
    }

	public static <T> Method getMethod(Class<T> clz, String prop, Class<?>... paramTypes){
		Method method = null;
		try {
	        method = clz.getMethod(prop, paramTypes);
        } catch (NoSuchMethodException | SecurityException e) {
	        e.printStackTrace();
        }
		return method;
	}

	public static Method getGetMethod(Class<?> clz, String prop) {
		String get = "get" + StringUtils.capitalize(prop);
		return getMethod(clz, get);

    }

	@SuppressWarnings("unchecked")
    public static <T> T invoke(Method method, Object instance, Class<T> returnClz) {
		Object result = null;
		try {
	        result = method.invoke(instance);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
	        e.printStackTrace();
        }
		return (T)result;
    }

	public static boolean hasField(Class<?> clz, String prop){
		return getField(clz, prop) != null ? true : false;
	}

	public static Field getField(Class<?> clz, String prop){
		Field field = null;
		try {
	        field = clz.getDeclaredField(prop);
        } catch (NoSuchFieldException | SecurityException e) {
        	e.printStackTrace();
        }
		return field;
	}

	public static Object getFieldVal(Field field, Object instance) {
		Object result = null;
	    try {
	    	if(!field.isAccessible()) field.setAccessible(true);
	    	result = field.get(instance);
        } catch (IllegalAccessException | SecurityException e) {
	        e.printStackTrace();
        }
	    return result;

    }

}
