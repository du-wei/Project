package com.webapp.builder;

import com.webapp.thrift.generator.builder.ThriftFileBuilder;

public class ThriftBuilder {
	
	public static void build(Class<?> clzInterface) {
		ThriftFileBuilder fileBuilder = new ThriftFileBuilder();
		try {
	        fileBuilder.buildToOutputStream(clzInterface, System.out);
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
	
}
