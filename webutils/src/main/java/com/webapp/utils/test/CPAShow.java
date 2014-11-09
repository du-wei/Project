package com.webapp.utils.test;

import java.util.Arrays;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.webapp.utils.datasort.DataSortUtils;
import com.webapp.utils.datasort.DataSortUtils.OrderType;

public class CPAShow {

	@Test
	public void main() throws Exception {
//		ThreadUtils.testSimpleCAP(CPAShow::testString, 20000000);
//		ThreadUtils.testSimpleCAP(CPAShow::testStringBuf, 20000000);
//		ThreadUtils.testCAP(CPAShow::spilt1, 2000000);
//		ThreadUtils.testCAP(CPAShow::spilt2, 2000000);
//		ThreadUtils.testSimpleCAP(CPAShow::copyArray, 1000000);
//		ThreadUtils.testSimpleCAP(CPAShow::loop1, 20000);
//		ThreadUtils.testSimpleCAP(CPAShow::loop2, 20000);
//		ThreadUtils.testSimpleCAP(CPAShow::loop3, 20000);
//		ThreadUtils.testSimpleCAP(CPAShow::compute, 1);
//		ThreadUtils.testSimpleCAP(CPAShow::compute1, 2000000);
		ThreadUtils.testSimpleCAP(CPAShow::utils, 100000000);
	}
	
	public static void utils(int loop) {
		for(int i=0; i<loop; i++){
			Instance.of(i).toStr();
		}
    }
	
	public static void compute(int loop) {//-------------->
	    //* 2 4 8 --> <<2
		/// 2 4 8 --> >>2
		int i = 5<<1;
    }
	public static void compute1(int loop) {
		int i = 5*2;
	}
	
	public static void spilt1(int loop) {//-------------->
	    String jj = "1,2,3,4,5,6,7,8,9,0";
		jj.split(",");
    }
	public static void spilt2(int loop) {
		String jj = "1,2,3,4,5,6,7,8,9,0";
		StringUtils.split(",");
	}
	
	public static void copyArray(int loop) {//-------------->
		int[] arr1 = new int[]{0, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		
		for (int i = 1; i < loop; i++) {
			System.arraycopy(arr1, 5, arr2, 5, 5);
		}
//		System.out.println(Arrays.toString(arr1));
//		System.out.println(Arrays.toString(arr2));
    }

	public static void testString(int loop) {//-------------->
		for (int i = 1; i < loop; i++) {
			String s1 = "Hello World Hello World";
			String s2 = "Hello World Hello World";
			String s3 = s1 + " " + s2;
		}
	}
	public static void testStringBuf(int loop) {
		for (int i = 1; i < loop; i++) {
			StringBuffer s = new StringBuffer(47);
			s.append("Hello World Hello World");
			s.append(" ");
			s.append("Hello World Hello World");
			String s3 = s.toString();
		}
	}
	
	private static void loop1(int loop) {
		String s = "";
		for (int i = 1; i < loop; i++) {
			s = s + "+" + i;
		}
	}
	private static void loop2(int loop) {//-------------->
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < loop; i++) {
			sb.append(i).append("+");
		}
	}
	private static void loop3(int loop) {//-------------->
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < loop; i++) {
			sb.append(i).append("+");
		}
	}
	
}
