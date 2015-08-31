package com.webapp.template.utils;

import com.webapp.thrift.generator.builder.ThriftFileBuilder;

public class ThriftGenerator {
	
	public static void build(Class<?> clzInterface) {
		ThriftFileBuilder fileBuilder = new ThriftFileBuilder();
		try {
	        fileBuilder.buildToOutputStream(clzInterface, System.out);
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
	
}
