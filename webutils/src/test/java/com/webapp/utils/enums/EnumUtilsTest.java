package com.webapp.utils.enums;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.datasort.DataSortUtils.OrderType;

public class EnumUtilsTest {

	String format = "%1$-25s\t%3$s -> %2$s\n";
	@Test
    public void isExist() throws Exception {
		boolean result = EnumUtils.isExist(OrderType.class, "key", "heap_desc");
		System.out.printf(format , "key exist heap_desc", result, "isExist()");
		assertThat(result, Matchers.is(true));
    }

	@Test
    public void isNotExist() throws Exception {
		boolean result = EnumUtils.isNotExist(OrderType.class, "key", "heap_desc");
		System.out.printf(format , "key exist heap_desc", result, "isNotExist()");
		assertThat(result, Matchers.is(false));
    }
	@Test
	public void valueOf() throws Exception {
		String result = EnumUtils.valueOf(OrderType.heap_desc, "key");
		System.out.printf(format , "key heap_desc", result, "valueOf()");
		assertThat(result, Matchers.is("heap_desc"));
	}
	@Test
	public void getEnum() throws Exception {
		OrderType result = EnumUtils.getEnum(OrderType.class, "key", "heap_desc");
		System.out.printf(format , "heap_desc", result, "getEnum()");
		assertThat(result, Matchers.is(OrderType.heap_desc));
	}

	@Test
    public void testName() throws Exception {
	    List<OrderType> result = EnumUtils.getEnums(OrderType.values(), OrderType.asce, OrderType.desc);
	    System.out.printf(format , "values", result, "getEnums()");
	    assertThat(result, Matchers.not(Matchers.contains(OrderType.asce)));
    }

	@Test
	public void getList() throws Exception {
		System.out.println(EnumUtils.getList(OrderType.class, "key"));
	}

}
