package com.webapp.utils.format;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class FormatUtils {

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
		NumberFormat format = NumberFormat.getNumberInstance();
		setFormat(scale, format);
		return format;
	}

	private static NumberFormat getPercentFormat(int scale) {
		NumberFormat format = NumberFormat.getPercentInstance();
		setFormat(scale, format);
		return format;
	}

	private static NumberFormat getCurrencyFormat(int scale) {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		setFormat(scale, format);
		return format;
	}

	private static void setFormat(int scale, NumberFormat format) {
		format.setRoundingMode(RoundingMode.HALF_UP);
		format.setMinimumFractionDigits(scale);
		format.setMaximumFractionDigits(scale);
	}

}
