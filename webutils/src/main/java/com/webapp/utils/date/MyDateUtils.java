package com.webapp.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

public class MyDateUtils {

	private static DateTime date = new DateTime();
	private static String defaultTime = "HH:mm:ss";
	private static String defaultDate = "yyyy-MM-dd";
	private static String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat format = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

	private static String[] parsePatterns = new String[] {
			"EEE MMM dd HH:mm:ss Z yyyy", //
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
			"yyyy.MM.dd G 'at' HH:mm:ss z", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
			"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm:ss",
			"yyyy/MM/dd HH:mm", "yyyy/MM/dd", "yyyy年MM月dd日", "HH:mm:ss",
			"h:mm a" };

	// ------- getDateTime -------
	public static String getCurDateTime() {
		return getCurDateTime(defaultFormat);
	}

	public static String getCurDateTime(String formatStr) {
		return DateTime.now().toString(formatStr);
	}

	public static String getDateTime(long source) {
		return getDateTime(source, defaultFormat);
	}

	public static String getDateTime(long source, String formatStr) {
		return date.withMillis(source).toString(formatStr);
	}

	public static String getDateTime(String source) {
		return getDateTime(source, defaultFormat);
	}

	public static String getDateTime(String source, String formatStr) {
		return parse(source).toString(formatStr);
	}

	// ------- getDate ------
	public static String getCurDate() {
		return getCurDateTime(defaultDate);
	}

	public static String getCurDate(String formatStr) {
		return getCurDateTime(formatStr);
	}

	public static String getDate(long source, String formatStr) {
		return getDateTime(source, formatStr);
	}

	public static String getDate(String source) {
		return getDateTime(source, defaultDate);
	}

	public static String getDate(String source, String formatStr) {
		return getDateTime(source, formatStr);
	}

	// ------- getTime ------
	public static String getCurTime() {
		return getCurDateTime(defaultTime);
	}

	public static String getCurTime(String formatStr) {
		return getCurDateTime(formatStr);
	}

	public static String getTime(long source, String formatStr) {
		return getDateTime(source, formatStr);
	}

	public static String getTime(String source) {
		return getDateTime(source, defaultTime);
	}

	public static String getTime(String source, String formatStr) {
		return getDateTime(source, formatStr);
	}

	// ------- parseStr ------+++
	public static Calendar parseStrToCal(String source) {
		return parse(source).toCalendar(Locale.getDefault());
	}

	public static Date parseStrToDate(String source) {
		return parse(source).toDate();
	}

	public static Calendar getCalByDate(Date date) {
		Calendar strCal = Calendar.getInstance();
		strCal.setTime(date);
		return strCal;
	}

	// ------- parse HH ------
	public static int getYearByStr(String source) {
		return parseStrToCal(source).get(Calendar.YEAR);
	}

	public static int getMonthByStr(String source) {
		return parseStrToCal(source).get(Calendar.MONTH);
	}

	public static int getDayByStr(String source) {
		return parseStrToCal(source).get(Calendar.DAY_OF_MONTH);
	}

	public static int getHourByStr(String source) {
		return parseStrToCal(source).get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinuteByStr(String source) {
		return parseStrToCal(source).get(Calendar.MINUTE);
	}

	public static int getSecondByStr(String source) {
		return parseStrToCal(source).get(Calendar.SECOND);
	}

	public static DateTime parseDate(String str) {
		return parseDate(str, parsePatterns);
	}

	public static DateTime parseDate(String str, String parsePattern) {
		return parseDate(str, new String[] { parsePattern });
	}

	private static DateTime parseDate(String str, String[] parsePattern) {
		try {
			return date.withMillis(DateUtils.parseDate(str, parsePattern)
					.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static DateTime parseGMT(String str) {
		try {
			return date.withMillis(format.parse(str).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static DateTime parseMillis(String millis) {
		return parseMillis(Long.parseLong(millis));
	}

	public static DateTime parseMillis(Long millis) {
		return date.withMillis(millis);
	}

	public static DateTime parse(String source) {
		if (source
				.matches("\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [\\+]?\\w{3,4} \\d{4}")) {
			return parseGMT(source);
		} else if (source.matches("\\d{13}")) {
			return parseMillis(source);
		}
		return parseDate(source);
	}

}
