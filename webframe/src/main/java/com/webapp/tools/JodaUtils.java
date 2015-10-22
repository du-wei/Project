package com.webapp.tools;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JodaUtils {
	
	public static JSONObject death(Date date, int age) {
		JSONObject result = new JSONObject();
		
		DateTime now = new DateTime();
		DateTime birthday = new DateTime(date);
	    DateTime death = birthday.plusYears(age);
	    
	    result.put("birthday", birthday.toString("yyyy-MM-dd"));
	    result.put("now", now.toString("yyyy-MM-dd"));
	    result.put("death", death.toString("yyyy-MM-dd"));
	    //已经过去的时间
	    Interval passed = new Interval(birthday.toDate().getTime(), now.toDate().getTime());
	    Duration pduration = passed.toDuration();
		result.put("ptDays", pduration.getStandardDays());
		result.put("ptHours", pduration.getStandardHours());
		result.put("ptMinutes", pduration.getStandardMinutes());
		result.put("ptSeconds", pduration.getStandardSeconds());
		Period pweek = passed.toPeriod(PeriodType.weeks());
		result.put("ptWeeks", pweek.getWeeks());
		Period pmonth = passed.toPeriod(PeriodType.months());
		result.put("ptMonths", pmonth.getMonths());
		
		Period pperiod = passed.toPeriod(PeriodType.yearMonthDayTime());
		result.put("pdYears", pperiod.getYears());
		result.put("pdMonths", pperiod.getMonths());
		result.put("pdDays", pperiod.getDays());
		result.put("pdHours", pperiod.getHours());
		result.put("pdMinutes", pperiod.getMinutes());
		result.put("pdSeconds", pperiod.getSeconds());
	    
		//将来的时间
	    Interval interval = new Interval(now.toDate().getTime(), death.toDate().getTime());
		Duration duration = interval.toDuration();
		result.put("tDays", duration.getStandardDays());
		result.put("tHours", duration.getStandardHours());
		result.put("tMinutes", duration.getStandardMinutes());
		result.put("tSeconds", duration.getStandardSeconds());
		
		Period week = interval.toPeriod(PeriodType.weeks());
		result.put("tWeeks", week.getWeeks());
		
		Period month = interval.toPeriod(PeriodType.months());
		result.put("tMonths", month.getMonths());
		
		Period period = interval.toPeriod(PeriodType.yearMonthDayTime());
		result.put("dYears", period.getYears());
		result.put("dMonths", period.getMonths());
		result.put("dDays", period.getDays());
		result.put("dHours", period.getHours());
		result.put("dMinutes", period.getMinutes());
		result.put("dSeconds", period.getSeconds());
		
		return result;
    }
	
}
