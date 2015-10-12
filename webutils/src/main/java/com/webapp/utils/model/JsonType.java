package com.webapp.utils.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JsonType {

	private int jkInt;
	private long jkLong;
	private double jkDouble;
	private boolean jkBool;

	private Date jkDate;
	private String str;
	private List<String> list;
	private Map<String, String> map;
	private Set<String> set;

	public int getJkInt() {
		return jkInt;
	}
	public void setJkInt(int jkInt) {
		this.jkInt = jkInt;
	}
	public long getJkLong() {
		return jkLong;
	}
	public void setJkLong(long jkLong) {
		this.jkLong = jkLong;
	}
	public double getJkDouble() {
		return jkDouble;
	}
	public void setJkDouble(double jkDouble) {
		this.jkDouble = jkDouble;
	}
	public boolean isJkBool() {
		return jkBool;
	}
	public void setJkBool(boolean jkBool) {
		this.jkBool = jkBool;
	}
	public Date getJkDate() {
		return jkDate;
	}
	public void setJkDate(Date jkDate) {
		this.jkDate = jkDate;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
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
	public Set<String> getSet() {
		return set;
	}
	public void setSet(Set<String> set) {
		this.set = set;
	}

}
