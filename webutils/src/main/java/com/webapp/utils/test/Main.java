package com.webapp.utils.test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.webapp.utils.json.JSONBeanUtils;
import com.webapp.utils.json.JSONUtils;
import com.webapp.utils.model.ModelUtils;
import com.webapp.utils.model.Student;



public class Main {

	public static void main(String[] args) {
				 Student stu = ModelUtils.getStu();

				String json = JSONUtils.of(stu).toString();
				System.out.println(json);

		//		System.out.println(json);

		//		JSONLexerBase lexer = new JSONScanner(json);
//				DefaultJSONParser jsonParser = new DefaultJSONParser(json);

		//		if(lexer.tokenName().equals("id")){
		//			lexer.nextToken();
		//		}
		//		jsonParser.setResolveStatus(DefaultJSONParser.TypeNameRedirect);
		//		jsonParser.config(Feature.InitStringFieldAsEmpty, true);


		//		JSONLexer lexer2 = jsonParser.getLexer();
		//		lexer2.skipWhitespace();
		//		System.out.println(jsonParser.getInput().length());
		//
		//		do{
		//			lexer2.nextToken();
		//			System.out.println(lexer2.stringVal());
		//		}while (lexer2.getBufferPosition() != jsonParser.getInput().length());
		//		lexer2.resetStringPosition();
		//
//				Student parseObject = jsonParser.parseObject(Student.class);
//				jsonParser.close();

				json = "{\"stu_no\":\"nJynz\",\"values\":\"2014-10-10 10:10:10\"}";

				Map<String, String> map = new HashMap<String, String>();
				map.put("stu_no", "stuNo");
				map.put("values", "birthday");

				Student parseObject = JSONBeanUtils.of(json, Student.class).mapCamel();
				System.out.println(JSON.toJSONString(parseObject, true));



//				System.out.println(JSON.toJSONString(json));


	}


}
