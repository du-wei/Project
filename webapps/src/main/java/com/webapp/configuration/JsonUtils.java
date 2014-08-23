package com.webapp.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;

public class JsonUtils {

	private static List<String> list = new ArrayList<>();
	private static Map<String, Object> map = new HashMap<>();
	static {
		list.add("first");
		list.add("second");
		map.put("name", "json");
		map.put("bool", Boolean.TRUE);
		map.put("int", new Integer(1));
		map.put("arr", new String[] { "a", "b" });
	}

	@Test
	public void objToJson() {
		Student stu = new Student();
		stu.setId(1);
		stu.setName("ok");
		stu.setAddress("chen");

		List<Object> lists = new ArrayList<>();
		lists.add("xx");
		lists.add(stu);
		JSONArray jsonArray = JSONArray.fromObject(list);
		JSONArray jsonArrays = JSONArray.fromObject(lists);
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonArray);
		System.out.println(jsonArrays);
		System.out.println(jsonObject);

		System.out.println(JSONArray.fromObject(stu).toString());
		System.out.println(JSONObject.fromObject(stu).toString());
		System.out.println(JSONSerializer.toJSON(stu).toString());
	}

}
