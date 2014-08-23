package com.webapp.collections;

import java.util.Arrays;

import org.junit.Test;

public class ArraysTest {

	@Test
	public void testArrays() {
		int[] count = new int[10];
		for (int i = 0; i < 10; i++) {
			count[i] = i;
		}

		p("+++++数组操作");
		p("-----原始数组  -------------:" + Arrays.toString(count));
		p("-----数组binarySearch()-8:" + Arrays.binarySearch(count, 9));
		p("-----数组equals()--------:" + Arrays.equals(count, count));
		p("-----数组copyOf()--------:" + Arrays.toString(Arrays.copyOf(count, 5)));
		p("-----数组copyOfRange()---:"
				+ Arrays.toString(Arrays.copyOfRange(count, 2, 10)));
		Arrays.sort(count);
		p("-----数组sort()----------:" + Arrays.toString(count));
		Arrays.fill(count, 110);
		p("-----数组fill()----------:" + Arrays.toString(count));
		p("-----数组toString()------:" + Arrays.toString(count));
	}

	@Test
	public void testArray() {

	}

	private void p(Object o) {
		System.out.println(o);
	}
}
