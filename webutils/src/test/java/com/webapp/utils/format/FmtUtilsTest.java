package com.webapp.utils.format;

import static org.junit.Assert.assertThat;

import java.util.function.Supplier;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.thread.ThreadUtils;

public class FmtUtilsTest {

	String format = "%1$-10s\t%3$s -> %2$s\n";
    int data1 = 1000;
	float data2 = 2000.0f;
	double data3 = 3000.0d;
	String data4 = "$4000.000";
	long data5 = 5000;
	double data6 = 0.0d;

	@Test
	public void fmtNum() {
	    String data = "2.65145";

	    double result = FmtUtils.of(data).endZero(true).toDouble(2);
		System.out.printf(format , data, result, "string\ttoDouble(2)");
		assertThat(result, Matchers.is(2.65));

		result = FmtUtils.of(data1).endZero(false).toDouble(2);
		System.out.printf(format , data1, result, "int\ttoDouble(2)");
		assertThat(result, Matchers.is(1000.0));

		result = FmtUtils.of(data2).endZero(false).toDouble(2);
		System.out.printf(format , data2, result, "float\ttoDouble(2)");
		assertThat(result, Matchers.is(2000.0));

		result = FmtUtils.of(data3).endZero(true).toDouble(2);
		System.out.printf(format , data3, result, "double\ttoDouble(2)");
		assertThat(result, Matchers.is(3000.0));

		result = FmtUtils.of(data4).endZero(true).toDouble(2);
		System.out.printf(format , data4, result, "string\ttoDouble(2)");
		assertThat(result, Matchers.is(4000.0));
    }

	@Test
    public void fmtScale() throws Exception {

		String result = FmtUtils.of(data1).fmt(2);
		System.out.printf(format , data1, result, "int\tfmt(2)");
		assertThat(result, Matchers.is("1000.00"));

		result = FmtUtils.of(data2).fmt(2);
		System.out.printf(format , data2, result, "float\tfmt(2)");
		assertThat(result, Matchers.is("2000.00"));

		result = FmtUtils.of(data3).fmt(2);
		System.out.printf(format , data3, result, "double\tfmt(2)");
		assertThat(result, Matchers.is("3000.00"));

		result = FmtUtils.of(data4).fmt(2);
		System.out.printf(format , data4, result, "string\tfmt(2)");
		assertThat(result, Matchers.is("4000.00"));

		result = FmtUtils.of(data5).fmt(2);
		System.out.printf(format , data5, result, "long\tfmt(2)");
		assertThat(result, Matchers.is("5000.00"));

		result = FmtUtils.of(data6).fmt(2);
		System.out.printf(format , data6, result, "long\tfmt(2)");
		assertThat(result, Matchers.is("0.00"));
		System.out.println("-------------------------------------->");
    }

	@Test
    public void fmt1() throws Exception {
		String fmt = "0.0000";

		String result = FmtUtils.of(data1).fmt(fmt);
		System.out.printf(format , data1, result, "int\tfmt("+fmt+")");
		assertThat(result, Matchers.is("1000.0000"));

		result = FmtUtils.of(data2).fmt(fmt);
		System.out.printf(format , data2, result, "float\tfmt("+fmt+")");
		assertThat(result, Matchers.is("2000.0000"));

		result = FmtUtils.of(data3).fmt(fmt);
		System.out.printf(format , data3, result, "double\tfmt("+fmt+")");
		assertThat(result, Matchers.is("3000.0000"));

		result = FmtUtils.of(data4).fmt(fmt);
		System.out.printf(format , data4, result, "string\tfmt("+fmt+")");
		assertThat(result, Matchers.is("4000.0000"));

		result = FmtUtils.of(data5).fmt(fmt);
		System.out.printf(format , data5, result, "long\tfmt("+fmt+")");
		assertThat(result, Matchers.is("5000.0000"));

		result = FmtUtils.of(data6).fmt(fmt);
		System.out.printf(format , data6, result, "long\tfmt("+fmt+")");
		assertThat(result, Matchers.is("0.0000"));
		System.out.println("-------------------------------------->");
	}

	@Test
    public void fmt2() throws Exception {

		String fmt = "$0.0000";

		String result = FmtUtils.of(data1).fmt(fmt);
		System.out.printf(format , data1, result, "int\tfmt("+fmt+")");
		assertThat(result, Matchers.is("$1000.0000"));

		result = FmtUtils.of(data2).fmt(fmt);
		System.out.printf(format , data2, result, "float\tfmt("+fmt+")");
		assertThat(result, Matchers.is("$2000.0000"));

		result = FmtUtils.of(data3).fmt(fmt);
		System.out.printf(format , data3, result, "double\tfmt("+fmt+")");
		assertThat(result, Matchers.is("$3000.0000"));

		result = FmtUtils.of(data4).fmt(fmt);
		System.out.printf(format , data4, result, "string\tfmt("+fmt+")");
		assertThat(result, Matchers.is("$4000.0000"));

		result = FmtUtils.of(data5).fmt(fmt);
		System.out.printf(format , data5, result, "long\tfmt("+fmt+")");
		assertThat(result, Matchers.is("$5000.0000"));

		result = FmtUtils.of(data6).fmt(fmt);
		System.out.printf(format , data6, result, "long\tfmt("+fmt+")");
		assertThat(result, Matchers.is("$0.0000"));
		System.out.println("-------------------------------------->");
    }

	@Test
    public void fmtCurrency() throws Exception {

		String result = FmtUtils.of(data1).fmtCurrency();
		System.out.printf(format , data1, result, "int\tfmtCurrency()");
		assertThat(result, Matchers.is("￥1000"));

		result = FmtUtils.of(data2).fmtCurrency();
		System.out.printf(format , data2, result, "float\tfmtCurrency()");
		assertThat(result, Matchers.is("￥2000.0"));

		result = FmtUtils.of(data3).fmtCurrency();
		System.out.printf(format , data3, result, "double\tfmtCurrency()");
		assertThat(result, Matchers.is("￥3000.0"));

		result = FmtUtils.of(data4).fmtCurrency();
		System.out.printf(format , data4, result, "string\tfmtCurrency()");
		assertThat(result, Matchers.is("￥4000.000"));

		result = FmtUtils.of(data5).fmtCurrency();
		System.out.printf(format , data5, result, "long\tfmtCurrency()");
		assertThat(result, Matchers.is("￥5000"));

		result = FmtUtils.of(data6).fmtCurrency(2);
		System.out.printf(format , data6, result, "long\tfmtCurrency()");
		assertThat(result, Matchers.is("￥0.00"));
		System.out.println("-------------------------------------->");
    }

	@Test
    public void fmtDollar() throws Exception {

		String result = FmtUtils.of(data1).fmtDollar();
		System.out.printf(format , data1, result, "int\tfmtDollar()");
		assertThat(result, Matchers.is("$1000"));

		result = FmtUtils.of(data2).fmtDollar();
		System.out.printf(format , data2, result, "float\tfmtDollar()");
		assertThat(result, Matchers.is("$2000.0"));

		result = FmtUtils.of(data3).fmtDollar();
		System.out.printf(format , data3, result, "double\tfmtDollar()");
		assertThat(result, Matchers.is("$3000.0"));

		result = FmtUtils.of(data4).fmtDollar();
		System.out.printf(format , data4, result, "string\tfmtDollar()");
		assertThat(result, Matchers.is("$4000.000"));

		result = FmtUtils.of(data5).fmtDollar();
		System.out.printf(format , data5, result, "long\tfmtDollar()");
		assertThat(result, Matchers.is("$5000"));

		result = FmtUtils.of(data6).fmtDollar(2);
		System.out.printf(format , data6, result, "long\tfmtDollar()");
		assertThat(result, Matchers.is("$0.00"));
		System.out.println("-------------------------------------->");
    }

	@Test
    public void fmtPercent() throws Exception {

		String result = FmtUtils.of(data1).fmtPercent();
		System.out.printf(format , data1, result, "int\tfmtPercent()");
		assertThat(result, Matchers.is("100000%"));

		result = FmtUtils.of(data2).fmtPercent();
		System.out.printf(format , data2, result, "float\tfmtPercent()");
		assertThat(result, Matchers.is("200000.0%"));

		result = FmtUtils.of(data3).fmtPercent();
		System.out.printf(format , data3, result, "double\tfmtPercent()");
		assertThat(result, Matchers.is("300000.0%"));

		result = FmtUtils.of(data4).fmtPercent();
		System.out.printf(format , data4, result, "string\tfmtPercent()");
		assertThat(result, Matchers.is("400000.000%"));

		result = FmtUtils.of(data5).fmtPercent();
		System.out.printf(format , data5, result, "long\tfmtPercent()");
		assertThat(result, Matchers.is("500000%"));

		result = FmtUtils.of(data6).fmtPercent(2);
		System.out.printf(format , data6, result, "long\tfmtPercent()");
		assertThat(result, Matchers.is("0.00%"));
		System.out.println("-------------------------------------->");
    }


	@Test
    public void muiltFmt() throws Exception {
		int data1 = 1000;

		Supplier<Runnable> run = () -> {
			return () -> {
				System.out.println(FmtUtils.of(data1).fmt("#.0000"));
			};
		};


		ThreadUtils.testMultiCase(run, 2);

//		ThreadUtils.testSimpleCase(()->{System.out.println(FmtUtils.of(data1).fmt("#.0000"));}, 20000);
    }

}
