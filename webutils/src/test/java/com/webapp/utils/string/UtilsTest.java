package com.webapp.utils.string;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

	@Test
    public void testName() throws Exception {
		String data1 = "jk_king,";
		String data2 = "jk_king@";
		String data3 = "Jk_King";
		String data4 = "JkKingISO";
		String data5 = "ISOJkKing";

		String email = "jk_king@163.com";
		String mobile = "13621186235";
		List<String> list = Arrays.asList(new String[]{"a", "b", "c"});

		System.out.println(data1 + "\tdelTail(data) -> " + Utils.delTail(data1));
		System.out.println(data2 + "\tdelTail(data, remove) -> " + Utils.delTail(data2, "@"));
		System.out.println(email + "\tsafedEmail(email) -> " + Utils.safedEmail(email));
		System.out.println(email + "\tsafedEmail(email, len) -> " + Utils.safedEmail(email, 4));
		System.out.println(mobile + "\tsafedMobile(mobile) -> " + Utils.safedMobile(mobile));
		System.out.println(list + "\tsplit(list) -> " + Utils.split(list));
		System.out.println(list + "\tsplit(list, split) -> " + Utils.split(list, "-"));

		System.out.println("-------------------------------------->");
		System.out.println(data3 + "\ttoCamel(data) -> " + Utils.toCamel(data3));
		System.out.println(data4 + "\ttoCamel(data) -> " + Utils.toCamel(data4));
		System.out.println(data5 + "\ttoCamel(data) -> " + Utils.toCamel(data5));

		System.out.println("-------------------------------------->");
		System.out.println(data3 + "\ttoPascal(data) -> " + Utils.toPascal(data3));
		System.out.println(data4 + "\ttoPascal(data) -> " + Utils.toPascal(data4));
		System.out.println(data5 + "\ttoPascal(data) -> " + Utils.toPascal(data5));

		System.out.println("-------------------------------------->");
		System.out.println(data3 + "\ttoUnderline(data) -> " + Utils.toUnderline(data3));
		System.out.println(data4 + "\ttoUnderline(data) -> " + Utils.toUnderline(data4));
		System.out.println(data5 + "\ttoUnderline(data) -> " + Utils.toUnderline(data5));
    }

}
