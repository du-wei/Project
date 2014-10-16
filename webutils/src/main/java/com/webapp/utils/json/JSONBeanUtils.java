package com.webapp.utils.json;

import java.lang.reflect.Method;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.webapp.utils.string.Utils;

public class JSONBeanUtils<T> {

	private String json;
	private Class<T> clz;

	private JSONBeanUtils(String json, Class<T> clz) {
		this.json = json;
		this.clz = clz;
	}

	public static <T> JSONBeanUtils<T> of(String json, Class<T> clz) {
		return new JSONBeanUtils<T>(json, clz);
	}

	public T mapCamel(){
		ExtraProcessor processor = new ExtraProcessor() {
			public void processExtra(Object obj, String key, Object val) {
				@SuppressWarnings("unchecked")
                T t = (T)obj;
				mapField(key, val, t);
			}
		};
		return JSON.parseObject(json, clz, processor);
	}

	public T map(final Map<String, String> map){
		ExtraProcessor processor = new ExtraProcessor() {
			public void processExtra(Object obj, String key, Object val) {
				@SuppressWarnings("unchecked")
                T t = (T)obj;
				if(map.containsKey(key)){
					mapField(map.get(key), val, t);
				}
			}
		};
		return JSON.parseObject(json, clz, processor);
	}

	private void mapField(String key, Object val, T t) {
        Method[] methods = t.getClass().getMethods();
		for (Method method : methods) {
			String name = method.getName();
			String field = Utils.toPascal(key);
			if (name.equals("set" + field)) {
				try {
					method.invoke(t, JSON.parseObject(JSON.toJSONString(val), method.getParameterTypes()[0]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
    }

}
