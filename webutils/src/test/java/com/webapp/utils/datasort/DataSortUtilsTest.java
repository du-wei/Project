package com.webapp.utils.datasort;

import java.util.Arrays;

import org.junit.Test;

import com.webapp.utils.random.RandomUtils;

public class DataSortUtilsTest {

	String format = "%1$-40s\t%3$s -> %2$s\n";

	@Test
    public void sortByte() throws Exception {
		byte[] data = RandomUtils.nextBytes("kingadmin".getBytes());

	    String result = Arrays.toString(DataSortUtils.sort(data));
	    System.out.printf(format , Arrays.toString(data), result, "sort");
    }
	@Test
	public void sortChar() throws Exception {
		char[] data = RandomUtils.str(6).toCharArray();
		String result = Arrays.toString(DataSortUtils.sort(data));
	    System.out.printf(format , Arrays.toString(data), result, "sort");
	}
	@Test
	public void sortDouble() throws Exception {
		double[] data = RandomUtils.doubleArray(6, 2);
		String result = Arrays.toString(DataSortUtils.sort(data));
	    System.out.printf(format , Arrays.toString(data), result, "sort");
	}
	@Test
	public void sortFloat() throws Exception {
		float[] data = RandomUtils.floatArray(6, 2);
		String result = Arrays.toString(DataSortUtils.sort(data));
	    System.out.printf(format , Arrays.toString(data), result, "sort");
	}
	@Test
	public void sortInt() throws Exception {
		int[] data = RandomUtils.intArray(6, 200);
		String result = Arrays.toString(DataSortUtils.sort(data));
	    System.out.printf(format , Arrays.toString(data), result, "sort");
	}
	@Test
	public void sortLong() throws Exception {
		long[] data = RandomUtils.longArray(6, 200);
		String result = Arrays.toString(DataSortUtils.sort(data));
	    System.out.printf(format , Arrays.toString(data), result, "sort");
	}
	@Test
	public void sortShort() throws Exception {
		short[] data = new short[]{(short)RandomUtils.nextInt(), (short)RandomUtils.nextInt(), (short)RandomUtils.nextInt()};
		String result = Arrays.toString(DataSortUtils.sort(data));
	    System.out.printf(format , Arrays.toString(data), result, "sort");
	}
}
