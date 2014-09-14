package com.webapp.utils.t;

import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

public class Jdk8Utils {

	public static <T> void toString(T[] data) {
//		Arrays.stream(data).forEach(t->System.out.println(t));
//		Arrays.stream(data).forEach(System.out::println);
//		Stream.of(data).forEach(System.out::println);
		print(Arrays.asList(data));

	}


	public static boolean contains(int[] data, int valToFind){
		return contains(ArrayUtils.toObject(data), valToFind);
	}
	public static boolean contains(double[] data, double valToFind){
		return contains(ArrayUtils.toObject(data), valToFind);
	}
	public static boolean contains(float[] data, float valToFind){
		return contains(ArrayUtils.toObject(data), valToFind);
	}
	public static boolean contains(boolean[] data, boolean valToFind){
		return contains(ArrayUtils.toObject(data), valToFind);
	}
	public static boolean contains(long[] data, long valToFind){
		return contains(ArrayUtils.toObject(data), valToFind);
	}
	public static boolean contains(char[] data, char valToFind){
		return contains(ArrayUtils.toObject(data), valToFind);
	}
	public static <T> boolean contains(T[] data, T valToFind){
		return Stream.of(data).anyMatch(t->t.equals(valToFind));
	}

	public static<T> boolean isArray(T data){
		return data.getClass().isArray();
	}
	public static<T> boolean isBaseArray(T data){
		return isArray(data) && !data.getClass().getTypeName().contains(".");
	}

	public static void print(Object data){
		System.out.println(data);
	}



}
