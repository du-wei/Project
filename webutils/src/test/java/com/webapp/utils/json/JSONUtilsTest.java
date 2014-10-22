package com.webapp.utils.json;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.date.DateTools.FmtDate;
import com.webapp.utils.model.JavaType;
import com.webapp.utils.model.JsonType;
import com.webapp.utils.model.ModelUtils;

public class JSONUtilsTest {

	String format = "%1$s\t%3$-20s -> %2$s\n";
	@Test
    public void toStr() throws Exception {
		JavaType javaType = ModelUtils.getJavaType();
	    String result = JSONUtils.toString(javaType);
	    System.out.println(result);
    }

	@Test
    public void testName() throws Exception {
		JsonType jsonType = ModelUtils.getJsonType();

		String result = JSONUtils.of(jsonType).after("after", "after").toString();
		System.out.printf(format ,"", result, "after(key, val)");
		assertThat(result, Matchers.containsString("after"));

		result = JSONUtils.of(jsonType).before("before", "before").toString();
		System.out.printf(format ,"", result, "before(key, val)");
		assertThat(result, Matchers.containsString("before"));

		System.out.printf(format ,"", JSONUtils.of(jsonType).toCamelKey(), "toCamelKey()");
	    System.out.printf(format ,"", JSONUtils.of(jsonType).toPascalKey(), "toPascalKey()");
	    System.out.printf(format ,"", JSONUtils.of(jsonType).toUnderlineKey(), "toUnderlineKey()");
	    System.out.printf(format ,"", JSONUtils.of(jsonType).toBeanToArray(), "toBeanToArray()");

	    result = JSONUtils.of(jsonType).dateFormat(FmtDate.Fmt_DateTime).toString();
	    System.out.printf(format ,"", result, "dateFormat(fmt)");

	    result = JSONUtils.of(jsonType).doubleFormat("#.0000", JsonType.class).toString();
	    System.out.printf(format ,"", result, "doubleFormat(fmt, clz)");

    }

}
