package com.webapp.utils.t;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DynamicEnum {

	public static DynamicEnum getInstance() {
		return new DynamicEnum();
	}

	enum DataType {
		key("key"), val("val"), name("name"), code("code");

		private String type;

		public String getType() {
			return type;
		}

		DataType(String type) {
			this.type = type;
		}
	}

	private List<DynamicProp> list = new ArrayList<>();

	public void add(String val) {
		list.add(new DynamicProp(val));
	}
	public void add(String key, String val) {
		list.add(new DynamicProp(key, val));
	}
	public void add(String key, String val, String name) {
		list.add(new DynamicProp(key, val, name));
	}
	public void add(String key, String val, String name, String code) {
		list.add(new DynamicProp(key, val, name, code));
	}

	public boolean isExist(String prop, DataType type) {
		if (StringUtils.isNotEmpty(prop)){
			for(DynamicProp dp : list){
				if(StringUtils.isNotEmpty(dp.get(type)) && dp.get(type).equals(prop))
					return true;
			}
		}
		return false;
	}
	public boolean isNotExist(String prop, DataType type) {
		return !isExist(prop, type);
	}

	public DynamicProp getData(String prop, DataType type) {
		if (StringUtils.isNotEmpty(prop)){
			for(DynamicProp dp : list){
				if(dp.get(type).equals(prop))
					return dp;
			}
		}
		return null;
	}

	public String toStr(DataType type){
    	StringBuffer result = new StringBuffer("[");
    	for (DynamicProp dp : list){
    		result.append(dp.get(type) + ",");
    	}
    	return result.deleteCharAt(result.length()-1).append(']').toString();
    }


	class DynamicProp {
		private String key;
		private String val;
		private String name;
		private String code;

		public DynamicProp(String val) {
			super();
			this.val = val;
		}
		public DynamicProp(String key, String val) {
			super();
			this.key = key;
			this.val = val;
		}
		public DynamicProp(String key, String val, String name) {
			super();
			this.key = key;
			this.val = val;
			this.name = name;
		}
		public DynamicProp(String key, String val, String name, String code) {
			super();
			this.key = key;
			this.val = val;
			this.name = name;
			this.code = code;
		}

		public String getKey() {
			return key;
		}
		public String getVal() {
			return val;
		}
		public String getName() {
			return name;
		}
		public String getCode() {
			return code;
		}

		public String get(DataType type){
			if(type == DataType.key) return this.getKey();
			if(type == DataType.val) return this.getVal();
			if(type == DataType.name) return this.getName();
			if(type == DataType.code) return this.getCode();
			return null;
		}

	}

}
