package com.webapp.utils.wrun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

public class GuavaUtils {

	public static void main(String[] args) {
		HashMap<Object, Object> map1 = Maps.newHashMap();
		HashMap<String, String> map2 = Maps.newHashMap(ImmutableMap.of("key", "val"));

		ArrayList<Object> list1 = Lists.newArrayList();
		ArrayList<String> list2 = Lists.newArrayList("one", "two");
		List<String> asList = Arrays.asList("fff");

		HashSet<Object> set1 = Sets.newHashSet();
		HashSet<String> set2 = Sets.newHashSet("one", "two");
		Integer[] array = ObjectArrays.newArray(Integer.class, 2);

		// 字符串连接
		String join = Joiner.on("-").join(1, 2, 3);
		System.out.println(join);
		System.out.println(Joiner.on("-").skipNulls().join(new String[] { "1", "2", "3" }));
		System.out.println(Joiner.on("-").appendTo(new StringBuilder("A:"), 1, 2, 3));
		System.out.println(Joiner.on("-").useForNull("x").join(1, null, 3));
		System.out.println(Joiner.on("#").withKeyValueSeparator("=").join(ImmutableMap.of(1, 2, 3, 4)));

		// 字符串分割
		System.out.println(Splitter.on(",").splitToList("1,2,4"));
		System.out.println(Splitter.onPattern("\\s+").trimResults().splitToList("1 \t 2 4"));
		System.out.println(Splitter.fixedLength(2).splitToList("134"));
		System.out.println(Splitter.on("#").withKeyValueSeparator(":").split("1:2#3:4"));
//		CharMatcher
//		CaseFormat
		
		Ordering<Comparable> nullsLast = Ordering.natural().nullsLast();
//		Preconditions

		List<Integer> sortedCopy = Ordering.natural().sortedCopy(Arrays.asList(1,4,8,3));
		System.out.println(sortedCopy);
		
	}
	

}
