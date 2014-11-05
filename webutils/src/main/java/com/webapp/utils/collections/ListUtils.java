package com.webapp.utils.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtils {

	public static <T> List<T> unique(List<T> list) {
	    Set<T> set = new HashSet<T>(list);
	    list.clear();
	    list.addAll(set);
	    return list;
    }

	@SafeVarargs
    public static <T> List<T> asList(T...a) {
		List<T> asList = new ArrayList<>(Arrays.asList(a));
		return asList;
	}

}
