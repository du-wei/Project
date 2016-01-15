package com.webapp.utils.collectionns;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.collections.ListUtils;

public class ListUtilsTest {

	@Test
    public void unique() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("aa");
		list.add("aa");

		System.out.println(ListUtils.unique(list));
		assertThat(ListUtils.unique(list).size(), Matchers.is(1));
    }

}
