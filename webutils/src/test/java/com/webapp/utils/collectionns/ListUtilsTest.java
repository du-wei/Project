package com.webapp.utils.collectionns;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.webapp.utils.collections.ListUtils;

public class ListUtilsTest {

	@Test
    public void unique() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("aa");
		list.add("aa");

		System.out.println(ListUtils.unique(list));
    }
}
