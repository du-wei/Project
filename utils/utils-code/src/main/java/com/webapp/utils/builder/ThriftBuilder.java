package com.webapp.utils.builder;

import com.webapp.utils.thrift.builder.ThriftFileBuilder;

public final class ThriftBuilder {

	public static void build(Class<?> clzInterface) {
		ThriftFileBuilder fileBuilder = new ThriftFileBuilder();
		try {
	        fileBuilder.buildToOutputStream(clzInterface, System.out);
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }

}
