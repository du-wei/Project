package com.webapp.utils.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Jdk8Utils<T, R> {

	public static void main(String[] args) {
		int[] jj = {1, 2};
		String[] jjj = {"aa", "bb"};
//		Jdk8Utils.of(jj).toString();
//		Jdk8Utils.of(jj).ok();
		System.out.println(Jdk8Utils.of(jj).paixu());

	}

	private T[] objArr;
//	private T type;
	private R baseArr;
	private Jdk8Utils(T[] data){
		this.objArr = data;
	}
	private Jdk8Utils(R data){
		this.baseArr = data;
	}
	private static<T, R> Jdk8Utils<T, R> newInstance(T[] data) {
		return new Jdk8Utils<T, R>(data);
	}
	private static<T, R> Jdk8Utils<T, R> newInstance(R data) {
		return new Jdk8Utils<T, R>(data);
	}

	public static Jdk8Utils<Integer, int[]> of(int[] data){
		return newInstance(data);
	}
//	public static Jdk8Utils<Double> of(double[] data){
//		return newInstance(ArrayUtils.toObject(data));
//	}
//	public static Jdk8Utils<Float> of(float[] data){
//		return newInstance(ArrayUtils.toObject(data));
//	}
//	public static Jdk8Utils<Boolean> of(boolean[] data){
//		return newInstance(ArrayUtils.toObject(data));
//	}
//	public static Jdk8Utils<Long> of(long[] data){
//		return newInstance(ArrayUtils.toObject(data));
//	}
//	public static Jdk8Utils<Short> of(short[] data){
//		return newInstance(ArrayUtils.toObject(data));
//	}
//	public static Jdk8Utils<Byte> of(byte[] data){
//		return newInstance(ArrayUtils.toObject(data));
//	}
//	public static Jdk8Utils<Character> of(char[] data){
//		return newInstance(ArrayUtils.toObject(data));
//	}
	public static<T, R> Jdk8Utils<T, R> of(T[] data){
		return newInstance(data);
	}

	public boolean contains(T valToFind){
		return Stream.of(objArr).anyMatch(t->t.equals(valToFind));
	}

	@Override
	public String toString(){
		List<T> asList = Arrays.asList(objArr);
		System.out.println(asList);
		return asList.toString();

	}

	public R paixu(){
		toString();
		objArr[0] = null;
		toString();
		return null;
	}

//	public static<T> boolean isArray(T data){
//		return data.getClass().isArray();
//	}
//	public static<T> boolean isBaseArray(T data){
//		return isArray(data) && !data.getClass().getTypeName().contains(".");
//	}
//
//	public static void print(Object data){
//		System.out.println(data);
//	}

}
