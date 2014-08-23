package com.webapp.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public static void main(String[] args) throws Exception {
		List<String> ok = new ArrayList<>();
		ok.add("ok");
		ok.add("chenglong");

		JSONArray jArray = toJsonArray(ok);

		System.err.println(jArray.getString(0));

		Map<String, String> map = new HashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		System.err.println(JSONObject.fromObject(map).opt("c"));

	}

	public static JSONObject toJsonObject(String jsonString) {
		return JSONObject.fromObject(jsonString);
	}

	public static JSONArray toJsonArray(Collection<? extends Object> collection) {
		return JSONArray.fromObject(collection);
	}

}
