package com.webapp.utils.model;

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

	private byte bbyte;
	private short bshort;
	private int bint;
	private long blong;
	private float bfloat;
	private double bdouble;
	private char bchar;
	private boolean bbool;

	private String str;
	private List<String> list;
	private Map<String, String> map;
	private Set<String> set;

	public byte getBbyte() {
		return bbyte;
	}
	public void setBbyte(byte bbyte) {
		this.bbyte = bbyte;
	}
	public short getBshort() {
		return bshort;
	}
	public void setBshort(short bshort) {
		this.bshort = bshort;
	}
	public int getBint() {
		return bint;
	}
	public void setBint(int bint) {
		this.bint = bint;
	}
	public long getBlong() {
		return blong;
	}
	public void setBlong(long blong) {
		this.blong = blong;
	}
	public float getBfloat() {
		return bfloat;
	}
	public void setBfloat(float bfloat) {
		this.bfloat = bfloat;
	}
	public double getBdouble() {
		return bdouble;
	}
	public void setBdouble(double bdouble) {
		this.bdouble = bdouble;
	}
	public char getBchar() {
		return bchar;
	}
	public void setBchar(char bchar) {
		this.bchar = bchar;
	}
	public boolean isBbool() {
		return bbool;
	}
	public void setBbool(boolean bbool) {
		this.bbool = bbool;
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
