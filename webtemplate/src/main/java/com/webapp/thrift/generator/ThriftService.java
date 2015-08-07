/**
 * 
 */
package com.webapp.thrift.generator;

import java.util.List;

public class ThriftService {
	
	private String name;
	
	private List<ThriftMethod> methods;

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
	 * @return the methods
	 */
	public List<ThriftMethod> getMethods() {
		return methods;
	}

	/**
	 * @param methods the methods to set
	 */
	public void setMethods(List<ThriftMethod> methods) {
		this.methods = methods;
	}
	
	
}
