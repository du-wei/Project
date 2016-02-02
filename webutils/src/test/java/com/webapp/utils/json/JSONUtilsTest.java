package com.webapp.utils.json;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.date.DateTools.FmtDate;
import com.webapp.utils.model.JsonType;
import com.webapp.utils.model.ModelUtils;

public class JSONUtilsTest {

	String format = "%2$-25s -> %1$s\n";
	JsonType jsonType = ModelUtils.getJsonType();
	@Test
    public void toStr() throws Exception {
	    JSONUtils.toString(jsonType);
	    //System.out.println(result);
    }

	@Test
    public void beforeOrAfter() throws Exception {
		String result = JSONUtils.of(jsonType).after("after", "after").toString();
		System.out.printf(format, result, "after(key, val)");
		assertThat(result, Matchers.containsString("after"));

		result = JSONUtils.of(jsonType).before("before", "before").toString();
		System.out.printf(format, result, "before(key, val)");
		assertThat(result, Matchers.containsString("before"));

		System.out.println("-------------------------------------->");
	}

	@Test
    public void fmt() throws Exception {

		String result = JSONUtils.of(jsonType).toCamelKey();
		System.out.printf(format, result, "toCamelKey()");
		assertThat(result, Matchers.containsString("jkBool"));

		result = JSONUtils.of(jsonType).toPascalKey();
		System.out.printf(format, result, "toPascalKey()");
		assertThat(result, Matchers.containsString("JkBool"));

		result = JSONUtils.of(jsonType).toSnakeKey();
		System.out.printf(format, result, "toUnderlineKey()");
		assertThat(result, Matchers.containsString("jk_bool"));

		result = JSONUtils.of(jsonType).toBeanToArray();
		System.out.printf(format, result, "toBeanToArray()");
		assertThat(result, Matchers.not(Matchers.containsString("jkBool")));

	    result = JSONUtils.of(jsonType).dateFormat(FmtDate.Fmt_DateTime).toString();
	    System.out.printf(format, result, "dateFormat(fmt)");

	    result = JSONUtils.of(jsonType).doubleFormat("#.000000", JsonType.class).toString();
	    System.out.printf(format, result, "doubleFormat(fmt, clz)");
	    assertThat(result, Matchers.containsString("0000"));

	    System.out.println("-------------------------------------->");
    }

	@Test
    public void include() throws Exception {
		String result = JSONUtils.of(jsonType).exclude("jkBool").toString();
	    System.out.printf(format, result, "exclude(prop)");
	    assertThat(result, Matchers.not(Matchers.containsString("jkBool")));

	    result = JSONUtils.of(jsonType).include("jkBool").toString();
	    System.out.printf(format, result, "include(prop)");
	    assertThat(result, Matchers.containsString("jkBool"));

	    System.out.println("-------------------------------------->");
    }

	@Test
    public void modify() throws Exception {
		String result = JSONUtils.of(jsonType).modifyKey("jkBool", "jk+bool").toString();
	    System.out.printf(format, result, "modifyKey(prop)");
	    assertThat(result, Matchers.containsString("jk+bool"));

	    result = JSONUtils.of(jsonType).modifyVal("jkBool", false).toString();
	    System.out.printf(format, result, "modifyVal(key, val)");
	    assertThat(result, Matchers.containsString("false"));

	    result = JSONUtils.of(jsonType).modifyVal("str", "str", "regex").toString();
	    System.out.printf(format, result, "modifyVal(key, regex val)");
	    assertThat(result, Matchers.containsString("regex"));

	    result = JSONUtils.of(jsonType).filterVal("str", "strs").toString();
	    System.out.printf(format, result, "filterVal(key, regex)");
	    assertThat(result, Matchers.not(Matchers.containsString("str")));
	    System.out.println("-------------------------------------->");
    }

	@Test
    public void config() throws Exception {
		String result = JSONUtils.of(jsonType).toMapNullValue().toString();
	    System.out.printf(format, result, "toMapNullValue()");
//	    result = JSONUtils.of(jsonType).toNonStrKeyAsStr().toString();
//	    System.out.printf(format, result, "toNonStrKeyAsStr()");
//	    result = JSONUtils.of(jsonType).toNullNumAsZero().toString();
//	    System.out.printf(format, result, "toNonStrKeyAsStr()");

	    System.out.println("-------------------------------------->");
	}
}
