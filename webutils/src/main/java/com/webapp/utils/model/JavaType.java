package com.webapp.utils.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JavaType {

//	byte(字节) 	    8         -128 - 127                                           0
//	short(短整型)        16      -32768 - 32768                                         0
//	int(整型)           32   -2147483648-2147483648                                    0
//	long(长整型)        64   -9233372036854477808-9233372036854477808                  0
//	float(浮点型)       32  -3.40292347E+38-3.40292347E+38                            0.0f
//	double(双精度)	    64  -1.79769313486231570E+308-1.79769313486231570E+308        0.0d
//	char(字符型)        16         ‘ \u0000 - u\ffff ’                             ‘\u0000 ’
//	boolean(布尔型)     1         true/false                                         false

	private byte jkByte;
	private short jkShort;
	private int jkInt;
	private long jkLong;
	private float jkFloat;
	private double jkDouble;
	private char jkChar;
	private boolean jkBool;

	private Date jkDate;
	private String str;
	private List<String> list;
	private Map<String, String> map;
	private Set<String> set;
	public byte getJkByte() {
		return jkByte;
	}
	public void setJkByte(byte jkByte) {
		this.jkByte = jkByte;
	}
	public short getJkShort() {
		return jkShort;
	}
	public void setJkShort(short jkShort) {
		this.jkShort = jkShort;
	}
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
	public float getJkFloat() {
		return jkFloat;
	}
	public void setJkFloat(float jkFloat) {
		this.jkFloat = jkFloat;
	}
	public double getJkDouble() {
		return jkDouble;
	}
	public void setJkDouble(double jkDouble) {
		this.jkDouble = jkDouble;
	}
	public char getJkChar() {
		return jkChar;
	}
	public void setJkChar(char jkChar) {
		this.jkChar = jkChar;
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
