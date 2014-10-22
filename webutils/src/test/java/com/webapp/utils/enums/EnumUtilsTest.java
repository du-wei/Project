package com.webapp.utils.enums;

import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.datasort.DataSortUtils.OrderType;

public class EnumUtilsTest {

	@Test
    public void isExist() throws Exception {
		boolean result = EnumUtils.isExist(OrderType.class, "key", "heap_desc");
		assertThat(result, Matchers.is(true));
    }

	@Test
    public void isNotExist() throws Exception {
		boolean result = EnumUtils.isNotExist(OrderType.class, "key", "heap_desc");
		assertThat(result, Matchers.is(false));
    }
	@Test
	public void valueOf() throws Exception {
		String result = EnumUtils.valueOf(OrderType.binaryInsert_asce, "key");
		System.out.println(result);
		assertThat(result, Matchers.is("binaryInsert_asce"));
	}
	@Test
	public void getEnum() throws Exception {
		OrderType result = EnumUtils.getEnum(OrderType.class, "key", "heap_desc");
		System.out.println(result);
		assertThat(result, Matchers.is(OrderType.heap_desc));
	}
	@Test
	public void getList() throws Exception {
		System.out.println(EnumUtils.getList(OrderType.class, "key"));
	}

}
