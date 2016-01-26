package com.webapp.elastic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class EsUtils {

	protected static class BuildMapping {
		private String type;
		private Map<String, Map<String, Object>> mapping = new HashMap<>();
		private void addProp(String name, Map<String, Object> prop) {
			mapping.put(name, prop);
		}
		public BuildMapping type(String type){
			this.type = type;
			return this;
		}
		public BuildProp name(String name){
			return new BuildProp(name, this);
		}

		public XContentBuilder toXCBuilder(){
			XContentBuilder build = null;
			try {
				build = XContentFactory.contentBuilder(XContentType.JSON);
				build.map(toMap());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return build;
		}

		public Map<String, Object> toMap(){
			JSONObject map = new JSONObject();
			map.put("properties", JSON.parseObject(JSON.toJSONString(mapping)));

			Map<String, Object> result = new HashMap<>();
			result.put(type, map);

			return result;
		}

		public String toJson(){
			String result = "";
			try {
				result = toXCBuilder().string();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		public String getType(){
			return this.type;
		}
	}
	protected static class BuildProp {
		private String name;
		private BuildMapping mapping;
		private BuildProp(String name, BuildMapping mapping) {
			this.name = name;
			this.mapping = mapping;
		}
		private Map<String, Object> map = new HashMap<>();
		public BuildProp type(String type){
			map.put("type", type);
			return this;
		}
		public BuildProp store(String store){
			map.put("store", store);
			return this;
		}
		public BuildProp index(String index){
			map.put("index", index);
			return this;
		}
		public BuildProp analyzer(String analyzer){
			map.put("analyzer", analyzer);
			return this;
		}
		public BuildProp custom(String key, Object val){
			map.put(key, val);
			return this;
		}
		public BuildMapping done(){
			mapping.addProp(name, map);
			return this.mapping;
		}
	}

	public static BuildMapping buildSource(){
		return new BuildMapping();
	}

}
