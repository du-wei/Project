package com.webapp.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

public class MyDateUtils {

	private static Logger logger = LogManager.getLogger(MyDateUtils.class);

	private static DateTime date = new DateTime();
	private static final String defaultTime = "HH:mm:ss";
	private static final String defaultDate = "yyyy-MM-dd";
	private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String Fmt_DateTime_G = "EEE MMM dd HH:mm:ss Z yyyy";
	private static final String Fmt_DateTime_AT = "yyyy.MM.dd G 'at' HH:mm:ss z";
	private static final String Fmt_DateTime_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static final String Fmt_DateTime_T = "yyyy-MM-dd'T'HH:mm:ss";
	private static final String Fmt_DateTime = "yyyy-MM-dd HH:mm:ss";
	private static final String Fmt_DateTime_NS = "yyyy-MM-dd HH:mm";
	private static final String Fmt_Date = "yyyy-MM-dd";
	private static final String Fmt_DateTime_Slant = "yyyy/MM/dd HH:mm:ss";
	private static final String Fmt_DateTime_SNS = "yyyy/MM/dd HH:mm";
	private static final String Fmt_Date_Slant = "yyyy/MM/dd";
	private static final String Fmt_Date_CN = "yyyy年MM月dd日";
	private static final String Fmt_Time = "HH:mm:ss";
	private static final String Fmt_Time_M = "h:mm a";

	public static void main(String[] args) {
//		DateUtils.
    }

	private static SimpleDateFormat format = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

	private static String[] parsePatterns = new String[] {
			Fmt_DateTime_G, //
			Fmt_DateTime_AT, //
			Fmt_DateTime_TZ, //
			Fmt_DateTime_T, //
			Fmt_DateTime, //
			Fmt_DateTime_NS, //
			Fmt_Date, //
			Fmt_DateTime_Slant, //
			Fmt_DateTime_SNS, //
			Fmt_Date_Slant, //
			Fmt_Date_CN, //
			Fmt_Time, //
			Fmt_Time_M  //
	};

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
	public static Date parseDate(String source) {
		return parse(source).toDate();
	}
	public static Date parseDate(String source, String format) {
		return parse(source, new String[] { format }).toDate();
	}

	public static Calendar getCalByDate(Date date) {
		Calendar strCal = Calendar.getInstance();
		strCal.setTime(date);
		return strCal;
	}

	// ------- parse HH ------
	public static int getYear(String source) {
		return parse(source).getYear();
	}

	public static int getMonth(String source) {
		return parse(source).getMonthOfYear();
	}

	public static int getDayOfMonth(String source) {
		return parse(source).getDayOfMonth();
	}

	public static int getDayOfWeek(String source) {
		return parse(source).getDayOfWeek();
	}

	public static int getDayOfYear(String source) {
		return parse(source).getDayOfYear();
	}

	public static int getHour(String source) {
		return parse(source).getHourOfDay();
	}

	public static int getMinute(String source) {
		return parse(source).getMinuteOfHour();
	}

	public static int getSecond(String source) {
		return parse(source).getSecondOfMinute();
	}

	private static DateTime parse(String str) {
		return parse(str, parsePatterns);
	}

	private static DateTime parse(String str, String[] parsePattern) {
		try {
			return date.withMillis(DateUtils.parseDate(str, parsePattern).getTime());
		} catch (ParseException e) {
			logger.error(str + "解析出错", e);
			return null;
		}
	}

	public static DateTime parseGMT(String str) {
		try {
			return date.withMillis(format.parse(str).getTime());
		} catch (ParseException e) {
			logger.error(str + "解析出错", e);
			return null;
		}
	}

	public static DateTime parseMillis(String millis) {
		return parseMillis(Long.parseLong(millis));
	}

	public static DateTime parseMillis(Long millis) {
		return date.withMillis(millis);
	}

	public static DateTime parses(String source) {
		if (source
				.matches("\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [\\+]?\\w{3,4} \\d{4}")) {
			return parseGMT(source);
		} else if (source.matches("\\d{13}")) {
			return parseMillis(source);
		}
		return parse(source);
	}

}
