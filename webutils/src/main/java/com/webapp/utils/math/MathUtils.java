package com.webapp.utils.math;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {

	public static double random() {
		return Math.floor(Math.random() * 11);
	}

	public static int nextInt() {
		return ThreadLocalRandom.current().nextInt();
	}

	public static int nextInt(int n) {
		return ThreadLocalRandom.current().nextInt(n);
	}

	public static int nextInt(int least, int bound) {
		return ThreadLocalRandom.current().nextInt(least, bound);
	}

	public static double nextDouble() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public static double nextDouble(double n) {
		return ThreadLocalRandom.current().nextDouble(n);
	}

	public static double nextDouble(double least, double bound) {
		return ThreadLocalRandom.current().nextDouble(least, bound);
	}

	public static long nextLong() {
		return ThreadLocalRandom.current().nextLong();
	}

	public static long nextLong(long n) {
		return ThreadLocalRandom.current().nextLong(n);
	}

	public static long nextLong(long least, long bound) {
		return ThreadLocalRandom.current().nextLong(least, bound);
	}

	public static float nextFloat() {
		return ThreadLocalRandom.current().nextFloat();
	}

	public static void nextBytes(byte[] bytes) {
		ThreadLocalRandom.current().nextBytes(bytes);
	}

}
