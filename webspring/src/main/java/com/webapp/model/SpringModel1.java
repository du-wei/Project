package com.webapp.model;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringModel1 {
	private List<String> list;
	private Map<String, String> map;
	private String[] array;
	private Set<String> set;
	private Properties properties;
	private Integer constantValue;

	public Integer getConstantValue() {
		return constantValue;
	}

	public void setConstantValue(Integer constantValue) {
		this.constantValue = constantValue;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String[] getArray() {
		return array;
	}

	public void setArray(String[] array) {
		this.array = array;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath*:applicationConfig.xml");
		SpringModel1 t = (SpringModel1) ctx.getBean("test");
		t.printArray(t.getArray());
		t.printList(t.getList());
		t.printMap(t.getMap());
		t.printSet(t.getSet());
		t.printProperties(t.getProperties());
		System.out.println(t.getConstantValue());

	}

	public void printList(List<String> result) {
		System.out.println("list value:");
		for (Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
			String element = (String) iterator.next();
			System.out.println(element);
		}
	}

	public void printMap(Map<String, String> result) {
		System.out.println("map value:");
		for (Iterator<String> iterator = result.keySet().iterator(); iterator
				.hasNext();) {
			String element = (String) iterator.next();
			System.out.println(element);
		}
	}

	public void printSet(Set<String> result) {
		System.out.println("set value:");
		for (Iterator<String> iterator = result.iterator(); iterator.hasNext();) {
			String element = (String) iterator.next();
			System.out.println(element);
		}
	}

	public void printArray(String[] result) {
		System.out.println("array value:");
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}

	public void printProperties(Properties result) {

		System.out.println("properties value:");
		Enumeration enu2 = result.propertyNames();
		while (enu2.hasMoreElements()) {
			String key = (String) enu2.nextElement();
			System.out.println(key);
		}

	}

}