package com.webapp.utils.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

/**
* @ClassName: DateTools.java
* @Package com.webapp.utils.date
* @Description: date toos
* @author  king king
* @date 2014年10月15日 下午10:46:07
* @version V1.0
*/
public class DateTools {

	private static Logger logger = LogManager.getLogger(DateTools.class);
	/** date --> {@value} */
	public static final String Fmt_DateTime_G = "EEE MMM dd HH:mm:ss Z yyyy";
	/** date --> {@value} */
	public static final String Fmt_DateTime_AT = "yyyy.MM.dd G 'at' HH:mm:ss z";
	/** date --> {@value} */
	public static final String Fmt_DateTime_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	/** date --> {@value} */
	public static final String Fmt_DateTime_T = "yyyy-MM-dd'T'HH:mm:ss";
	/** date --> {@value} */
	public static final String Fmt_DateTime = "yyyy-MM-dd HH:mm:ss";
	/** date --> {@value} */
	public static final String Fmt_DateTime_NS = "yyyy-MM-dd HH:mm";
	/** date --> {@value} */
	public static final String Fmt_Date = "yyyy-MM-dd";
	/** date --> {@value} */
	public static final String Fmt_DateTime_Slant = "yyyy/MM/dd HH:mm:ss";
	/** date --> {@value} */
	public static final String Fmt_DateTime_SNS = "yyyy/MM/dd HH:mm";
	/** date --> {@value} */
	public static final String Fmt_Date_Slant = "yyyy/MM/dd";
	/** date --> {@value} */
	public static final String Fmt_Date_CN = "yyyy年MM月dd日";
	/** date --> {@value} */
	public static final String Fmt_Time = "HH:mm:ss";
	/** date --> {@value} */
	public static final String Fmt_Time_M = "h:mm a";

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
	private DateTime ofDate;
	private DateTools(DateTime date){
		this.ofDate = date;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(DateTools.of("1413379722312").format());
    }

	/** now date **/
	public static DateTools now(){
		return new DateTools(DateTime.now());
	}

	/**
	* @param date string date
	*/
	public static DateTools of(String date){
		return new DateTools(parse(date));
	}

	/**
	* @param date long date
	*/
	public static DateTools of(long date){
		return new DateTools(new DateTime().withMillis(date));
	}

	/**
	* @param date date
	*/
	public static DateTools of(Date date){
		return new DateTools(new DateTime().withMillis(date.getTime()));
	}

	/** To java.util.Date **/
	public Date toDate(){
		return ofDate.toDate();
	}

	/** To java.util.Calendar **/
	public Calendar toCalendar(){
		return ofDate.toCalendar(Locale.getDefault());
	}

	/** To org.joda.time.DateTime **/
	public DateTime toJoda(){
		return ofDate;
	}

	/** Get yyyy-MM-dd **/
	public String getDate() {
	    return ofDate.toString(Fmt_Date);
    }

	/** Get HH:mm:ss **/
	public String getTime() {
	    return ofDate.toString(Fmt_Time);
    }

	/** Get yyyy-MM-dd HH:mm:ss **/
	public String getDateTime() {
	    return ofDate.toString(Fmt_DateTime);
    }

	/** Get long date **/
	public long getMillis() {
	    return ofDate.getMillis();
    }

	/** format yyyy-MM-dd HH:mm:ss **/
	public String format() {
	    return ofDate.toString(Fmt_DateTime);
    }

	/** format by format **/
	public String format(String format) {
	    return ofDate.toString(format);
    }

	public boolean isAfterNow(){
		return ofDate.isAfterNow();
	}
	public boolean isAfter(String date){
		return ofDate.isAfter(parse(date).getMillis());
	}
	public boolean isAfter(Date date){
		return ofDate.isAfter(date.getTime());
	}

	public boolean isBeforeNow(){
		return ofDate.isBeforeNow();
	}
	public boolean isBeforeNow(String date){
		return ofDate.isBefore(parse(date).getMillis());
	}
	public boolean isBeforeNow(Date date){
		return ofDate.isBefore(date.getTime());
	}

	private static DateTime parse(String date) {
		return parse(date, parsePatterns);
	}
	private static DateTime parse(String date, String[] parsePattern) {
		DateTime dateTime = new DateTime();
		try {
			return dateTime.withMillis(date.matches("\\d{13}") ? Long.parseLong(date) : DateUtils.parseDate(date, parsePattern).getTime());
		} catch (ParseException e) {
			logger.error(date + " parse error", e);
			return null;
		}
	}


}
