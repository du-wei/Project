package com.webapp.program;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.webapp.utils.codec.CodeUtils;
import com.webapp.utils.datasort.DataSortUtils.OrderType;

public class CPAShow {

	@Test
	public void main() throws Exception {
//		ThreadUtils.computeTime(System.currentTimeMillis());
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
//		ThreadUtils.testSimpleCAP(CPAShow::compute2, 2000000000);
//		ThreadUtils.testSimpleCAP(CPAShow::compute3, 2000000000);
//		ThreadUtils.testSimpleCAP(CPAShow::compute4, 2000000000);
//		ThreadUtils.testSimpleCAP(CPAShow::tryTest2, 2000000000);
//		ThreadUtils.testSimpleCAP(CPAShow::tryTest, 2000000000);
//		ThreadUtils.testSimpleCAP(CPAShow::local1, 200000000);
//		ThreadUtils.testSimpleCAP(CPAShow::local2, 200000000);
//		ThreadUtils.testSimpleCAP(CPAShow::invoke1, 2000000);
//		ThreadUtils.testSimpleCAP(CPAShow::invoke, 2000000);

//		CPAShow cpaShow = new CPAShow();


	}

	public static void myself1(int loop){
	}
	public static void myself2(int loop){
		for(int i=0; i<loop; i++){

		}
	}


	public static void invoke(int loop){
		for(int i=0; i<loop; i++){
			try {
	        	Field field = OrderType.class.getDeclaredField("key");
		        field.setAccessible(true);
				field.get(OrderType.asce);
	        } catch (Exception e) {
	        }
		}
	}
	public static void invoke1(int loop) {
		for(int i=0; i<loop; i++){
			try {
				Method method = OrderType.class.getDeclaredMethod("getKey");
				Object invoke = method.invoke(OrderType.asce);
			} catch (Exception e) {}
		}
	}


	public static int local = 0;
	public static void local1(int loop){
		for(int i=0; i<loop; i++){
			local++;
		}
	}

	public static void local2(int loop){//-------------->
		int a = 0;
		for(int i=0; i<loop; i++){
			a++;
		}
	}

 	public static void tryTest(int loop){//-------------->
		int a=0;
		try {
			for(int i=0; i<loop; i++){
				a++;
			}
		} catch (Exception e) {

		}
	}

	public static void tryTest2(int loop){
		int a=0;
		for(int i=0; i<loop; i++){
			try {
				a++;
            } catch (Exception e) {
            }
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

	public static void compute2(int loop) {
		//(x&y)+((x^y)>>1) 平均值
		//(~x+1) 求相反数
		for (int i = 1; i < loop; i++) {
			int x = 1;
			int y = 7;
			x ^= y;
			y ^= x;
			x ^= y;
		}
	}
	public static void compute3(int loop) {
		for (int i = 1; i < loop; i++) {
			int x = 1;
			int y = 7;
			//时间换空间
			x=x+y;
			y=x-y;
			x=x-y;
		}
	}
	public static void compute4(int loop) {
		for (int i = 1; i < loop; i++) {
			int x = 1;
			int y = 7;
			int temp = 0;
			//时间换空间
			temp = x;
			x = y;
			y = temp;
		}
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

	public static void loop1(int loop) {
		String s = "";
		for (int i = 1; i < loop; i++) {
			s = s + "+" + i;
		}
	}
	public static void loop2(int loop) {//-------------->
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < loop; i++) {
			sb.append(i).append("+");
		}
	}
	public static void loop3(int loop) {//-------------->
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < loop; i++) {
			sb.append(i).append("+");
		}
	}

}
