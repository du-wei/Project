package com.webapp.program;

import org.junit.Test;

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
//		ThreadUtils.testSimpleCAP(CPAShow::compute2, 2000000000);
//		ThreadUtils.testSimpleCAP(CPAShow::compute3, 2000000000);
//		ThreadUtils.testSimpleCAP(CPAShow::compute4, 2000000000);

//		CPAShow cpaShow = new CPAShow();

	}

	public static void compute(int loop) {//-------------->
	    //* 2 4 8 --> <<2
		/// 2 4 8 --> >>2
		int i = 5<<1;
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
			//空间换时间
			temp = x;
			x = y;
			y = temp;
		}
	}

}
