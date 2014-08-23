package com.webapp.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class CollectionsTest {

	@Test
	public void testCollections() {

		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		p("+++++集合排序操作");
		p("-----list原始集合---------：" + list);
		Collections.shuffle(list);
		p("-----list随机排列shuffle()：" + list);
		Collections.reverse(list);
		p("-----list反转集合reverse()：" + list);
		Collections.sort(list);
		p("-----list排序集合 sort()---:" + list);
		Collections.swap(list, 4, 5);
		p("-----list交换集合 swap()---:" + list);
		Collections.rotate(list, 3);
		p("-----list集合  rotate()--3:" + list);

		p("\n+++++集合查找，替换操作");
		Collections.sort(list);
		p("-----list原始集合 -------------：" + list);
		p("-----list集合binarySearch()-8:" + Collections.binarySearch(list, 8));
		Collections.shuffle(list);
		p("-----list原始集合 -------------：" + list);
		p("-----list集合max()-----------:" + Collections.max(list));
		p("-----list集合min()-----------:" + Collections.min(list));
		p("-----list集合frequency()----9:" + Collections.frequency(list, 9));
		p("-----list集合indexOfSubList():"
				+ Collections.indexOfSubList(list, list));
		p("-----list集合lastOfSubList()-:"
				+ Collections.lastIndexOfSubList(list, list));
		p("-----list集合replaceAll()----:" + Collections.replaceAll(list, 1, 5));
		p("-----list原始集合 -------------：" + list);
		Collections.fill(list, 1);
		p("-----list集合fill()----------:" + list);

		p("\n+++++创建同步的集合对象");
		Collection<String> c = Collections
				.synchronizedCollection(new ArrayList<String>());
		List<String> l = Collections.synchronizedList(new ArrayList<String>());
		Set<String> s = Collections.synchronizedSet(new HashSet<String>());
		Map<String, String> m = Collections
				.synchronizedMap(new HashMap<String, String>());
		p("Collection<String> c = Collections.synchronizedCollection(new ArrayList<String>());\n"
				+ "List<String> l = Collections.synchronizedList(new ArrayList<String>());\n"
				+ "Set<String> s = Collections.synchronizedSet(new HashSet<String>());\n"
				+ "Map<String, String> m = Collections.synchronizedMap(new HashMap<String, String>());");

		p("\n+++++生成只读的集合对象");
		l = Collections.emptyList();
		s = Collections.singleton("googleadmin");
		m = Collections.unmodifiableMap(m);
		p("l = Collections.emptyList();\n"
				+ "s = Collections.singleton(\"googleadmin\");\n"
				+ "m = Collections.unmodifiableMap(m);\n");

	}

	public void p(Object o) {
		System.out.println(o);
	}

	void maps() {
		Map<String, String> map = new HashMap<String, String>();
		String value = "";

		for (String key : map.keySet()) {
			value = map.get(key);
		}

		String key = "";
		for (Entry<String, String> entry : map.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
		}

		for (String val : map.values()) {
		}
	}
}
