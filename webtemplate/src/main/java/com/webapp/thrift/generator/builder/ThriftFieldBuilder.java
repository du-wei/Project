package com.webapp.thrift.generator.builder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.webapp.thrift.generator.Generic;
import com.webapp.thrift.generator.ThriftEnum;
import com.webapp.thrift.generator.ThriftField;
import com.webapp.thrift.generator.ThriftStruct;

public class ThriftFieldBuilder {
	
	public ThriftField buildThriftField(ThriftStructBuilder structBuilder, Field field, List<ThriftStruct> structs, List<ThriftEnum> enums) {
		ThriftField thriftField = new ThriftField();
		thriftField.setName(field.getName());
		if(field.getName().equals("__PARANAMER_DATA")) {
			return null;
		}
		Type type = field.getGenericType();
		Generic generic = Generic.fromType(field.getGenericType());
		thriftField.setGenericType(generic);
		if(type instanceof ParameterizedType) {
			structBuilder.buildStrutsByGeneric(structs, generic, enums);
		}else {
			if(generic.isEnum() || generic.isStruct()) {
				generic.setJavaClass(field.getType());
				generic.setValue(field.getType().getSimpleName());
				generic.setJavaTypeName(field.getType().getSimpleName());
				if(generic.isStruct()) {
					structs.add(structBuilder.buildThriftStruct(field.getType(), structs, enums));
				}else {
					enums.add(structBuilder.buildThriftEnum(field.getType()));
				}
			}
		}
		return thriftField;
	}
}
