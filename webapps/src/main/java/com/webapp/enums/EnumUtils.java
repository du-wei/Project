package com.webapp.enums;

public enum EnumUtils {

	RED(1, "oo", 1), GREEN(2, "oo", 2), BLANK(3, "oo", 3), YELLOW(4, "xx", 4);
	EnumUtils(int index, String key, int val) {
		this.key = key;
		this.val = val;
		this.index = index;
	}

	private int index;
	private String key;
	private int val;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public static void main(String[] args) {
		// System.out.println(Color.GREEN.getKey());

	}

}
