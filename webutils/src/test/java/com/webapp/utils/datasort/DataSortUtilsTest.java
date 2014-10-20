package com.webapp.utils.datasort;

import java.util.Arrays;

import org.junit.Test;

import com.webapp.utils.random.RandomUtils;

public class DataSortUtilsTest {

	@Test
    public void sortByte() throws Exception {
		byte[] data = RandomUtils.nextBytes("kingadmin".getBytes());
		System.out.println("sortByte before -> " + Arrays.toString(data));
	    System.out.println("sortByte after  -> " + Arrays.toString(DataSortUtils.sort(data)));
    }
	@Test
	public void sortChar() throws Exception {
		char[] data = RandomUtils.str(10).toCharArray();
		System.out.println("sortChar before -> " + Arrays.toString(data));
		System.out.println("sortChar after  -> " + Arrays.toString(DataSortUtils.sort(data)));
	}
	@Test
	public void sortDouble() throws Exception {
		double[] data = RandomUtils.doubleArray(10, 2);
		System.out.println("sortDouble before -> " + Arrays.toString(data));
		System.out.println("sortDouble after  -> " + Arrays.toString(DataSortUtils.sort(data)));
	}
	@Test
	public void sortFloat() throws Exception {
		float[] data = RandomUtils.floatArray(10, 2);
		System.out.println("sortFloat before -> " + Arrays.toString(data));
		System.out.println("sortFloat after  -> " + Arrays.toString(DataSortUtils.sort(data)));
	}
	@Test
	public void sortInt() throws Exception {
		int[] data = RandomUtils.intArray(10, 200);
		System.out.println("sortInt before -> " + Arrays.toString(data));
		System.out.println("sortInt after  -> " + Arrays.toString(DataSortUtils.sort(data)));
	}
	@Test
	public void sortLong() throws Exception {
		long[] data = RandomUtils.longArray(10, 200);
		System.out.println("sortLong before -> " + Arrays.toString(data));
		System.out.println("sortLong after  -> " + Arrays.toString(DataSortUtils.sort(data)));
	}
	@Test
	public void sortShort() throws Exception {
		short[] data = new short[]{(short)RandomUtils.nextInt(), (short)RandomUtils.nextInt(), (short)RandomUtils.nextInt()};
		System.out.println("sortShort before -> " + Arrays.toString(data));
		System.out.println("sortShort after  -> " + Arrays.toString(DataSortUtils.sort(data)));
	}
}
