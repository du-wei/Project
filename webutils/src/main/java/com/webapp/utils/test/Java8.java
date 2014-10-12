package com.webapp.utils.test;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.junit.Test;

public class Java8  {

	public static void main(String[] args) {
		//函数式接口
//		new Thread(()->{ System.out.println("thread");}).start();
//		new Thread(()->System.out.println("thread")).start();
//		Runnable r1 = () -> {System.out.println("Hello Lambda!");};
//		Runnable r2 = () -> System.out.println("Hello Lambda!");
//		Comparator<Integer> cmp= (x, y) -> {return (x < y) ? -1 : 0;};
//		Comparator<Integer> cmp1= (x, y) -> (x < y) ? -1 : 0;


		Lambda lambda = (i)->System.out.println("xx");
		lambda.two(8);

		Consumer<String> c = System.out::println;
		c.accept("x");

		BiFunction<Long,Long,Integer> bf = Long::compare;

	}

	@Test
	public void testName() throws Exception {
		Lambda.start();
//		Lambda.isEmpty(null);
	}













}

