package com.webapp.utils.wrun;

import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

public class Jdk8Utils<T, R> {

	public static void main(String[] args) {
		int[] jj = {1, 2};
		String[] jjj = {"aa", "bb"};
		System.out.println(Jdk8Utils.of(jj).contains(1));
System.out.println(jjj.getClass().getName());
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
	public static Jdk8Utils<Double, double[]> of(double[] data){
		return newInstance(ArrayUtils.toObject(data));
	}
	public static Jdk8Utils<Float, float[]> of(float[] data){
		return newInstance(ArrayUtils.toObject(data));
	}
	public static Jdk8Utils<Long, long[]> of(long[] data){
		return newInstance(ArrayUtils.toObject(data));
	}
	public static Jdk8Utils<Short, short[]> of(short[] data){
		return newInstance(ArrayUtils.toObject(data));
	}

	public static<T, R> Jdk8Utils<T, R> of(T[] data){
		return newInstance(data);
	}

	public boolean contains(T valToFind){
		return Stream.of(objArr).anyMatch(t->t.equals(valToFind));
	}

}
