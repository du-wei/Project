package com.webapp.utils.format;

import java.util.function.Supplier;

import org.junit.Test;

import com.webapp.utils.test.ThreadUtils;

public class FmtUtilsTest {

//	@Test
    public void muiltFmt() throws Exception {
		int data1 = 1000;

		Supplier<Runnable> run = () -> {
			return () -> {
				System.out.println(FmtUtils.of(data1).fmt("#.0000"));
			};
		};


		ThreadUtils.testMultiCase(run, 200);

		ThreadUtils.testSimpleCase(()->{System.out.println(FmtUtils.of(data1).fmt("#.0000"));}, 20000);
    }

	@Test
    public void fmt() throws Exception {
		int data1 = 1000;
		float data2 = 2000.0f;
		double data3 = 3000.0d;
		String data4 = "$4000.000";
		long data5 = 5000;

		System.out.println(data1 + "\t\tint    	fmt(2) -> " + FmtUtils.of(data1).fmt(2));
		System.out.println(data2 + "\t\tfloat  	fmt(2) -> " + FmtUtils.of(data2).fmt(2));
		System.out.println(data3 + "\t\tdouble 	fmt(2) -> " + FmtUtils.of(data3).fmt(2));
		System.out.println(data4 + "\tstring 	fmt(2) -> " + FmtUtils.of(data4).fmt(2));
		System.out.println(data5 + "\t\tlong 	fmt(2) -> " + FmtUtils.of(data5).fmt(2));

		System.out.println("-------------------------------------->");
		System.out.println(data1 + "\t\tint 	fmt(#.0000) -> " + FmtUtils.of(data1).fmt("#.0000"));
		System.out.println(data2 + "\t\tfloat  	fmt(#.0000) -> " + FmtUtils.of(data2).fmt("#.0000"));
		System.out.println(data3 + "\t\tdouble 	fmt(#.0000) -> " + FmtUtils.of(data3).fmt("#.0000"));
		System.out.println(data4 + "\tstring 	fmt(#.0000) -> " + FmtUtils.of(data4).fmt("#.0000"));
		System.out.println(data5 + "\t\tlong	fmt(#.0000) -> " + FmtUtils.of(data5).fmt("#.0000"));

		System.out.println("-------------------------------------->");
		System.out.println(data1 + "\t\tint 	fmt($#.00) -> " + FmtUtils.of(data1).fmt("$#.00"));
		System.out.println(data2 + "\t\tfloat  	fmt($#.00) -> " + FmtUtils.of(data2).fmt("$#.00"));
		System.out.println(data3 + "\t\tdouble 	fmt($#.00) -> " + FmtUtils.of(data3).fmt("$#.00"));
		System.out.println(data4 + "\tstring 	fmt($#.00) -> " + FmtUtils.of(data4).fmt("$#.00"));
		System.out.println(data5 + "\t\tlong	fmt($#.00) -> " + FmtUtils.of(data5).fmt("$#.00"));

		System.out.println("-------------------------------------->");
		System.out.println(data1 + "\t\tint 	fmtCurrency() -> " + FmtUtils.of(data1).fmtCurrency());
		System.out.println(data2 + "\t\tfloat  	fmtCurrency() -> " + FmtUtils.of(data2).fmtCurrency());
		System.out.println(data3 + "\t\tdouble 	fmtCurrency() -> " + FmtUtils.of(data3).fmtCurrency());
		System.out.println(data4 + "\tstring 	fmtCurrency() -> " + FmtUtils.of(data4).fmtCurrency());
		System.out.println(data5 + "\t\tlong	fmtCurrency() -> " + FmtUtils.of(data5).fmtCurrency());



    }


}
