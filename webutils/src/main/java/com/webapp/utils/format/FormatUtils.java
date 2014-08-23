package com.webapp.utils.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatUtils {

	// 循环执行时间长短由数组的内存访问次数决定的，而非整型数的乘法运算次数
	public static void main(String[] args) {
		int[] arr = new int[64 * 1024 * 1024 * 2];

		long begin = System.currentTimeMillis();
		for (int i = 0; i < arr.length; i++) {
			arr[i] *= 3;
		}
		long temp = System.currentTimeMillis();
		System.out.println(temp - begin);

		long temp1 = System.currentTimeMillis();
		for (int i = 0; i < arr.length; i += 2) {
			arr[i] *= 3;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - temp1);

	}

	public static String formatByDecimal(double val, int scale) {
		return BigDecimal.valueOf(val).setScale(scale, RoundingMode.HALF_UP)
				.toString();
	}

	public static String formatByDecimal(long val, int scale) {
		return BigDecimal.valueOf(val).setScale(scale, RoundingMode.HALF_UP)
				.toString();
	}

	public static String formatByDecimal(String val, int scale) {
		return BigDecimal.valueOf(Double.parseDouble(val))
				.setScale(scale, RoundingMode.HALF_UP).toString();
	}

	public static String formatByNumber(double val, int scale) {
		return getNumberFormat(scale).format(val);
	}

	public static String formatByNumber(long val, int scale) {
		return getNumberFormat(scale).format(val);
	}

	public static String formatByNumber(String val, int scale) {
		return getNumberFormat(scale).format(Double.parseDouble(val));
	}

	public static String formatByPercent(double val, int scale) {
		return getPercentFormat(scale).format(val);
	}

	public static String formatByPercent(long val, int scale) {
		return getPercentFormat(scale).format(val);
	}

	public static String formatByPercent(String val, int scale) {
		return getPercentFormat(scale).format(Double.parseDouble(val));
	}

	public static String formatByCurrency(double val, int scale) {
		return getCurrencyFormat(scale).format(val);
	}

	public static String formatByCurrency(long val, int scale) {
		return getCurrencyFormat(scale).format(val);
	}

	public static String formatByCurrency(String val, int scale) {
		return getCurrencyFormat(scale).format(Double.parseDouble(val));
	}

	private static NumberFormat getNumberFormat(int scale) {
		NumberFormat format = DecimalFormat.getNumberInstance();
		setFormat(scale, format);
		return format;
	}

	private static NumberFormat getPercentFormat(int scale) {
		NumberFormat format = DecimalFormat.getPercentInstance();
		setFormat(scale, format);
		return format;
	}

	private static NumberFormat getCurrencyFormat(int scale) {
		NumberFormat format = DecimalFormat.getCurrencyInstance();
		setFormat(scale, format);
		return format;
	}

	private static void setFormat(int scale, NumberFormat format) {
		format.setRoundingMode(RoundingMode.HALF_UP);
		format.setMinimumFractionDigits(scale);
		format.setMaximumFractionDigits(scale);
	}

}
