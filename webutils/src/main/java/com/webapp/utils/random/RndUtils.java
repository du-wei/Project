package com.webapp.utils.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.lang3.RandomStringUtils;

import com.webapp.utils.math.MathUtils;

public class RndUtils {

	private static String[] fixStrs = fixStr();

	public static ArrayList<String> arrayList(int size) {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(fixStrs[i % 10]);
		}
		return list;
	}

	public static LinkedList<String> linkedList(int size) {
		LinkedList<String> list = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			list.add(fixStrs[i % 10]);
		}
		return list;
	}

	public static HashSet<String> hashSet(int size) {
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < size; i++) {
			set.add(fixStrs[i % 10]);
		}
		return set;
	}

	public static TreeSet<String> treeSet(int size) {
		TreeSet<String> set = new TreeSet<>();
		for (int i = 0; i < size; i++) {
			set.add(fixStrs[i % 10]);
		}
		return set;
	}

	public static ConcurrentSkipListSet<String> concurrentSkipListSet(int size) {
		ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<>();
		for (int i = 0; i < size; i++) {
			set.add(fixStrs[i % 10]);
		}
		return set;
	}

	public static SortedMap<Integer, String> treeMap(int size) {
		SortedMap<Integer, String> map = new TreeMap<>();
		for (int i = 0; i < size; i++) {
			map.put(size - i, fixStrs[i % 10]);
		}
		return map;
	}

	public static ConcurrentSkipListMap<Integer, String> concurrentSkipListMap(
			int size) {
		ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
		for (int i = 0; i < size; i++) {
			map.put(size - i, fixStrs[i % 10]);
		}
		return map;
	}

	public static HashMap<Integer, String> hashMap(int size) {
		HashMap<Integer, String> map = new HashMap<>();
		for (int i = 0; i < size; i++) {
			map.put(i, fixStrs[i % 10]);
		}
		return map;
	}

	public static ConcurrentHashMap<Integer, String> concurrentHashMap(int size) {
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
		for (int i = 0; i < size; i++) {
			map.put(i, fixStrs[i % 10]);
		}
		return map;
	}

	public static String rndStr(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public static String rndStrNum(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public static String getStr(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = MathUtils.nextInt(62);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}

	public static Integer[] intArray(int size, int limit) {
		Integer[] result = new Integer[size];
		for (int i = 0; i < size; i++) {
			result[i] = MathUtils.nextInt(limit);
		}
		return result;
	}

	private static String[] fixStr() {
		String str = "EbPyzMuQfA-DQTWBEuXsj-ikKoMxzZtQ-exjhPMtWic-WTrqRijtYa-oIqEFtEQYm-IeLSowjPAK-UBscvKyNRo-uxBTiIPvBT-hksyujtzdB";
		return str.split("-");
	}
}
