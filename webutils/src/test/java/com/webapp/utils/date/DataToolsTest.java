package com.webapp.utils.date;

import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.date.DateTools.FmtDate;

public class DataToolsTest {

	String format = "%1$-30s\t%3$s -> %2$s\n";
	String data = "2014-10-10 10:10:10";

	@Test
    public void compare() throws Exception {

		boolean result = DateTools.of(data).isBeforeNow();
	    System.out.printf(format , data, result, "isBeforeNow()");
	    assertThat(result, Matchers.is(true));

	    result = DateTools.of(data).isBefore(new Date());
	    System.out.printf(format , data, result, "isBefore(data)");
	    assertThat(result, Matchers.is(true));

	    result = DateTools.of(data).isBefore("2014-10-11 10:10:10");
	    System.out.printf(format , data, result, "isBefore(2014-10-11 10:10:10)");
	    assertThat(result, Matchers.is(true));

	    result = DateTools.of(data).isAfterNow();
	    System.out.printf(format , data, result, "isAfterNow()");
	    assertThat(result, Matchers.is(false));

	    result = DateTools.of(data).isAfter(new Date());
	    System.out.printf(format , data, result, "isAfter(data)");
	    assertThat(result, Matchers.is(false));

	    result = DateTools.of(data).isAfter("2014-10-11 10:10:10");
	    System.out.printf(format , data, result, "isAfter(2014-10-11 10:10:10)");
	    assertThat(result, Matchers.is(false));
    }

	@Test
    public void fomat() throws Exception {
		String format = "%1$s\t%3$-40s -> %2$s\n";

	    String result = DateTools.of(data).format(FmtDate.Fmt_Date);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_Date+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_Date_CN);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_Date_CN+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_Date_Slant);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_Date_Slant+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_AT);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_AT+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_UTC);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_UTC+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_Z);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_Z+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_NS);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_NS+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_Slant);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_Slant+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_SNS);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_SNS+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_T);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_T+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_DateTime_TZ);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_DateTime_TZ+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_Time);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_Time+")");

	    result = DateTools.of(data).format(FmtDate.Fmt_Time_M);
	    System.out.printf(format , data, result, "format("+FmtDate.Fmt_Time_M+")");

    }

	@Test
	public void parse() throws Exception {
		String format = "%1$-30s\t%3$s -> %2$s\n";
		String data0 = "Fri Oct 10 10:10:10 GMT 2014";
		String data1 = "Fri Oct 10 10:10:10 CST 2014";
		String data2 = "Fri Oct 10 10:10:10 +0800 2014";
		String data3 = "星期五 十月 10 10:10:10 CST 2014";
		String data4 = "星期五 十月 10 10:10:10 +0800 2014";
		String data5 = "2014-10-10T10:10:10.000+0800";
		String data6 = "2014-10-10 10:10:10";
		String data7 = "2014.10.10 公元 at 10:10:10 CST";
		String data8 = "2014年10月10日";
		String data9 = "10:10:10";

		System.out.printf(format , data0, DateTools.of(data0).format(), "of()");
		System.out.printf(format , data1, DateTools.of(data1).format(), "of()");
		System.out.printf(format , data2, DateTools.of(data2).format(), "of()");
		System.out.printf(format , data3, DateTools.of(data3).format(), "of()");
		System.out.printf(format , data4, DateTools.of(data4).format(), "of()");
		System.out.printf(format , data5, DateTools.of(data5).format(), "of()");
		System.out.printf(format , data6, DateTools.of(data6).format(), "of()");
		System.out.printf(format , data7, DateTools.of(data7).format(), "of()");
		System.out.printf(format , data8, DateTools.of(data8).format(), "of()");
		System.out.printf(format , data9, DateTools.of(data9).format(), "of()");
	}

	@Test
    public void getDateOrTime() throws Exception {
	    String result = DateTools.of(data).getDateTime();
	    System.out.printf(format , data, result, "getDateTime()");

	    result = DateTools.of(data).getDate();
	    System.out.printf(format , data, result, "getDate()");

	    result = DateTools.of(data).getTime();
	    System.out.printf(format , data, result, "getTime()");
    }

	@Test
    public void toOther() throws Exception {
		System.out.printf(format , data, DateTools.of(data).toCalendar(), "toCalendar()");

		System.out.printf(format , data, DateTools.of(data).toDate(), "toDate()");

		System.out.printf(format , data, DateTools.of(data).toJoda(), "toJoda()");

    }

}
