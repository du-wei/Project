package com.webapp.utils.date;

import java.text.ParseException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @ClassName: DateTools.java
* @Package com.webapp.utils.date
* @Description: date toos
* @author  king king
* @date 2014年10月15日 下午10:46:07
* @version V1.0
*/
public final class DateTool {

	private DateTool(LocalDateTime dt){
		this.dt = dt;
	}

	public static LocalDate nowDate(){
		return LocalDate.now();
	}
	public static LocalTime nowTime(){
		return LocalTime.now();
	}
	public static LocalDateTime now(){
		return LocalDateTime.now();
	}

	/**
    * 	Instant——它代表的是时间戳
    *	LocalDate——不包含具体时间的日期
    *	LocalTime——它代表的是不含日期的时间
    *	LocalDateTime——它包含了日期及时间，没有偏移信息或者说时区。
    *	ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
    */

	private static final Logger logger = LoggerFactory.getLogger(DateTool.class);

	private LocalDateTime dt;
	private static final ThreadLocal<DateTool> local = new ThreadLocal<DateTool>();
	private static final String[] parsePatterns = new String[] {
		FmtDate.Fmt_DateTime_UTC, //
		FmtDate.Fmt_DateTime_Z, //
		FmtDate.Fmt_DateTime_AT, //
		FmtDate.Fmt_DateTime_TZ, //
		FmtDate.Fmt_DateTime_TZ_Z, //
		FmtDate.Fmt_DateTime_T, //
		FmtDate.Fmt_DateTime, //
		FmtDate.Fmt_DateTime_NS, //
		FmtDate.Fmt_Date, //
		FmtDate.Fmt_DateTime_Slant, //
		FmtDate.Fmt_DateTime_SNS, //
		FmtDate.Fmt_Date_Slant, //
		FmtDate.Fmt_Date_CN, //
		FmtDate.Fmt_Time, //
		FmtDate.Fmt_Time_M  //
	};

	public static String[] getPatterns(){
		return parsePatterns;
	}

	public static boolean isLocalEn(String date){
		return date.matches("\\w{3} \\w{3} \\d{2} \\d{2}:\\d{2}:\\d{2} [\\+]?\\w{3,4} \\d{4}");
	}

	public static interface FmtDate{

		/** date --> {@value} */
		public static final String Fmt_DateTime_UTC = "EEE MMM dd hh:mm:ss zzz yyyy";
		/** date --> {@value} */
		public static final String Fmt_DateTime_Z = "EEE MMM dd HH:mm:ss Z yyyy";
		/** date --> {@value} */
		public static final String Fmt_DateTime_AT = "yyyy.MM.dd G 'at' HH:mm:ss z";
		/** date --> {@value} */
		public static final String Fmt_DateTime_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

		public static final String Fmt_DateTime_TZ_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";
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

	}

	public static DateTool of(String date){
		return localData(parse(date));
	}

	public static DateTool of(long date){
		return localData(parse(date));
	}

	public static DateTool of(Date date){
		return localData(parse(date.getTime()));
	}

	private static DateTool localData(LocalDateTime dt) {
    	if (local.get() == null) local.set(new DateTool(dt));
		return local.get().setDate(dt);
	}

	private DateTool setDate(LocalDateTime dt) {
	    this.dt = dt;
	    return this;
    }

	private static LocalDateTime parse(String date) {
		return parse(date, parsePatterns);
	}

	private static LocalDateTime parse(String date, String[] parsePattern) {
		Long result = null;
		try {
			if(date.matches("\\d{13}")){
				result = Long.parseLong(date);
			}else if(isLocalEn(date)){
				result = DateUtils.parseDate(date, Locale.ENGLISH, parsePattern).getTime();
			}else {
				result = DateUtils.parseDate(date, parsePattern).getTime();
			}
			return parse(result);
		} catch (ParseException e) {
			logger.error(date + " parse error", e);
		}
		return LocalDateTime.now();
	}
	private static LocalDateTime parse(Long date) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
	}

	public String format(){
		//格式化不够强大
		return dt.format(DateTimeFormatter.ofPattern(FmtDate.Fmt_DateTime_TZ_Z));
	}

}
