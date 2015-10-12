package com.webapp.utils.wrun;

import java.util.Optional;
import java.util.function.Function;

//@FunctionalInterface
public interface Lambda {

//	java.util.function
//    Function<T, R>——将T作为输入，返回R作为输出
//    Predicate<T>——将T作为输入，返回一个布尔值作为输出
//    Consumer<T>——将T作为输入，不返回任何内容
//    Supplier<T>——没有输入，返回T
//    BinaryOperator<T>——将两个T作为输入，返回一个T作为输出
//	  UnaryOperator<T>

//	java.util.stream
//	中间操作包括
//	filter、map、flatMap、peel、distinct、sorted、limit和substream。
//	终止操作包括
//	forEach、toArray、reduce、collect、min、max、count、anyMatch、allMatch、noneMatch、findFirst和findAny
//	java.util.stream.Collectors

	void two(int i);

	//接口中可以提供默认的实现
	default void ok(){
		System.out.println("xx");
	}

	//接口中可以定义静态方法
	public static void start() {
		//方法调用
		//String::valueOf
		//x ->String.valueOf(x)
		Function<Integer, String> fun = String::valueOf;
		String val = fun.apply(111);

		System.out.println(val);

	}

	public static String isEmpty(String val) {
		Optional<String> opt = Optional.ofNullable(val);
		System.out.println( "val " + opt.isPresent() );
		System.out.println( "val1 " + opt.orElseGet( () -> "[none]" ) );
		System.out.println( opt.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );

		return opt.map(s -> s + "!").orElse("null");
	}

}
