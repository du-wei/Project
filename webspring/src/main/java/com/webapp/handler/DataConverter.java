package com.webapp.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.webapp.utils.model.Student;

@Component
public class DataConverter implements Converter<String, Student>{

	@Override
    public Student convert(String param) {
		if(StringUtils.isEmpty(param))
			return new Student();
		
		String[] split = param.split("-");
		Student student = new Student();
		student.setId(Integer.parseInt(split[0]));
		student.setStuNo(split[1]);
		student.setName(split[2]);
	    return student;
    }

}

//class MyConverter2 implements ConverterFactory<String, Student>{
//
//	@Override
//    public <T extends Student> Converter<String, T> getConverter(Class<T> clz) {
//	    return null;
//    }
//}

//class MyConverter3 implements GenericConverter{
//
//	@Override
//    public Object convert(Object paramObject,
//            TypeDescriptor paramTypeDescriptor1,
//            TypeDescriptor paramTypeDescriptor2) {
//	    return null;
//    }
//
//	@Override
//    public Set<ConvertiblePair> getConvertibleTypes() {
//	    return null;
//    }
//}