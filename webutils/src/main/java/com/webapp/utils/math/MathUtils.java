package com.webapp.utils.math;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtils {

	@Deprecated
	public static double random() {
		return Math.floor(Math.random() * 11);
	}

	public static int nextInt() {
		return ThreadLocalRandom.current().nextInt();
	}

	public static int nextInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	public static int nextInt(int least, int bound) {
		return ThreadLocalRandom.current().nextInt(least, bound);
	}

	public static double nextDouble() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public static double nextDouble(double bound) {
		return ThreadLocalRandom.current().nextDouble(bound);
	}

	public static double nextDouble(double least, double bound) {
		return ThreadLocalRandom.current().nextDouble(least, bound);
	}

	public static long nextLong() {
		return ThreadLocalRandom.current().nextLong();
	}

	public static long nextLong(long bound) {
		return ThreadLocalRandom.current().nextLong(bound);
	}

	public static long nextLong(long least, long bound) {
		return ThreadLocalRandom.current().nextLong(least, bound);
	}

	public static float nextFloat() {
		return ThreadLocalRandom.current().nextFloat();
	}

	public static boolean nextBoolean() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	public static void nextBytes(byte[] bytes) {
		ThreadLocalRandom.current().nextBytes(bytes);
	}

}
