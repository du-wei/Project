/**
 * 
 */
package com.webapp.utils.thrift;

public class ThriftEnumField {
	private String name;
	private Integer value;
	
	
	/**
	 * @param name
	 * @param value
	 */
	public ThriftEnumField(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}
