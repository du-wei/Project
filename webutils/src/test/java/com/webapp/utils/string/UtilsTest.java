package com.webapp.utils.string;

import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

public class UtilsTest {

	String format = "%1$-20s\t%3$s -> %2$s\n";

	@Test
    public void delTail() throws Exception {
		String data1 = "jk_king,";
		String data2 = "jk_king@";

		String result = Utils.delTail(data1);
		System.out.printf(format ,data1, result, "delTail(data)");
		assertThat(result, Matchers.is("jk_king"));

		result = Utils.delTail(data2, "@");
		System.out.printf(format ,data2, result, "delTail(data, remove)");
		assertThat(result, Matchers.is("jk_king"));
    }

	@Test
    public void safed() throws Exception {
		String email = "jk_king@163.com";
		String mobile = "13621186235";

		String result = Utils.safedEmail(email);
		System.out.printf(format ,email, result, "safedEmail(email)");
		assertThat(result, Matchers.is("jk_k***@163.com"));

		result = Utils.safedEmail(email, 4);
		System.out.printf(format ,email, result, "safedEmail(email, len)");
		assertThat(result, Matchers.is("jk_***@163.com"));

		result = Utils.safedMobile(mobile);
		System.out.printf(format ,mobile, result, "safedMobile(mobile)");
		assertThat(result, Matchers.is("136****6235"));
    }

	@Test
    public void spilt() throws Exception {
		List<String> list = Arrays.asList(new String[]{"a", "b", "c"});

		String result = Utils.split(list);
		System.out.printf(format ,list, result, "split(list)");

		result = Utils.split(list, "-");
		System.out.printf(format ,list, result, "split(list, split)");
    }

	@Test
    public void toCamel() throws Exception {
		String data1 = "Jk_King";
		String data2 = "JkKingISO";
		String data3 = "ISOJkKing";

		System.out.printf(format ,data1, Utils.toCamel(data1), "toCamel(data)");
		System.out.printf(format ,data2, Utils.toCamel(data2), "toCamel(data)");
		System.out.printf(format ,data3, Utils.toCamel(data3), "toCamel(data)");

		System.out.println("-------------------------------------->");
		System.out.printf(format ,data1, Utils.toPascal(data1), "toPascal(data)");
		System.out.printf(format ,data2, Utils.toPascal(data2), "toPascal(data)");
		System.out.printf(format ,data3, Utils.toPascal(data3), "toPascal(data)");

		System.out.println("-------------------------------------->");
		System.out.printf(format ,data1, Utils.toUnderline(data1), "toUnderline(data)");
		System.out.printf(format ,data2, Utils.toUnderline(data2), "toUnderline(data)");
		System.out.printf(format ,data3, Utils.toUnderline(data3), "toUnderline(data)");
    }

	@Test
    public void pinyin() throws Exception {
		String data1 = "中国";

		System.out.printf(format ,data1, Utils.toPinyin(data1), "toPinyin(data)");
		System.out.printf(format ,data1, Utils.toPinyin(data1, "-"), "toPinyin(data,separator)");
		System.out.printf(format ,data1, Utils.toShortPinyin(data1), "toShortPinyin(data)");
    }

	@Test
    public void isChinese() throws Exception {
		String data1 = "jk中国";

		boolean result = Utils.isChinese(data1);
		System.out.printf(format ,data1, result, "isChinese(data)");
		assertThat(result, Matchers.is(true));
    }

	@Test
    public void regex() throws Exception {
		String idcard = "130825200001011011";
		String email = "jk_king@163.com";
		String mobile = "13621186235";

		boolean result = Utils.regexEmail(email);
		System.out.printf(format ,email, result, "regexEmail(data)");
		assertThat(result, Matchers.is(true));

		result = Utils.regexIdcard(idcard);
		System.out.printf(format ,idcard, result, "regexIdcard(data)");
		assertThat(result, Matchers.is(true));

		result = Utils.regexMobile(mobile);
		System.out.printf(format ,mobile, result, "regexMobile(data)");
		assertThat(result, Matchers.is(true));

    }

}
