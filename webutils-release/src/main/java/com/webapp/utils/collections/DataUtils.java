package com.webapp.utils.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DataUtils {

	public static <V> void viewList(List<V> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public static <V> void viewSet(Set<V> set) {
		Iterator<V> iterator = set.iterator();
		for (; iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}

	public static <K, V> void viewMapKey(Map<K, V> map) {
		Iterator<K> iterator = map.keySet().iterator();
		for (; iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}

	public static <K, V> void viewMapVal(Map<K, V> map) {
		Collection<V> temp = map.values();
		for (Iterator<V> i = temp.iterator(); i.hasNext();) {
			System.out.println(i.next());
		}
	}

	public static <K, V> void viewMapKeyVal(Map<K, V> map) {
		Iterator<Entry<K, V>> entry = map.entrySet().iterator();
		for (; entry.hasNext();) {
			Entry<K, V> temp = entry.next();
			System.out.println(temp.getKey() + "-" + temp.getValue());
		}
	}

}
