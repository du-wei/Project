package com.webapp.utils.news;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

import com.webapp.utils.model.Student;

public class NewJavaTest {
	
//	java.util.function
//  Function<T, R>——将T作为输入，返回R作为输出
//  Predicate<T>——将T作为输入，返回一个布尔值作为输出
//  Consumer<T>——将T作为输入，不返回任何内容
//  Supplier<T>——没有输入，返回T
//  BinaryOperator<T>——将两个T作为输入，返回一个T作为输出
//	  UnaryOperator<T>
	
	@Test
    public void testStream() throws Exception {
	    
    }
	
	@Test
    public void testPredicate() throws Exception {
	    Predicate<String> pd1 = (s) -> s.length() > 0;
	    Predicate<String> pd2 = (s) -> s.length() == 0;
	    
	    System.out.println(pd1.test("ff"));
	    System.out.println(pd1.and(pd2).test("ff"));
	    System.out.println(pd1.or(pd2).test("ff"));
	    System.out.println(pd1.negate().test("ff"));
	    
	    
	    Predicate<String> pd3 = String::isEmpty;
	    System.out.println(pd3.test("jj"));
    }
	
	@Test
    public void testFunction() throws Exception {
	    Function<String, Double> fun1 = Double::valueOf;
	    Function<String, String> fun2 = fun1.andThen(String::valueOf);
	    Function<Object, Double> fun3 = fun1.compose(String::valueOf);
	    
	    System.out.println(fun1.apply("123"));
	    System.out.println(fun2.apply("1234"));
	    System.out.println(fun3.apply("12345"));
    }
	
	@Test
    public void testSupplier() throws Exception {
	    Supplier<Student> stuSupplier = Student::new;
	    
	    System.out.println(stuSupplier.get());
    }
	
	@Test
    public void testConsumer() throws Exception {
	    Consumer<Student> stuConsumer = (s)->System.out.println(s.getName());
	    stuConsumer.accept(new Student());
    }
	
	@Test
    public void testOptional() throws Exception {
		Optional<String> opt = Optional.ofNullable("jj");
		System.out.println( "val " + opt.isPresent() );
		System.out.println( "val1 " + opt.orElseGet( () -> "[none]" ) );
		System.out.println( opt.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }
	
	@Test
    public void testIntSummaryStatistics() throws Exception {
		List<Integer> list = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics summary = list.stream().mapToInt((x)->x).summaryStatistics();
		System.out.println("Highest in List : " + summary.getMax());  
		System.out.println("Lowest in List : " + summary.getMin());  
		System.out.println("Sum numbers : " + summary.getSum());  
		System.out.println("Average numbers : " + summary.getAverage());
    }
	
	@Test
    public void testNewCollections() throws Exception {
		List<String> list = new ArrayList<String>();
	    list.add("a");
	    list.add("c");
	    list.add("b");
	    list.add("d");

	    list.stream().filter((s)->s.startsWith("b")).forEach(System.out::println);
	    
	    list.stream().sorted().forEach(System.out::print);
	    System.out.println();
//	    list.stream().forEachOrdered(System.out::print);
//	    System.out.println();
	    
	    list.stream().map(String::toUpperCase).forEach(System.out::print);
	    System.out.println();

	    Optional<String> reduce = list.stream().reduce((s1, s2)->s1 + "#" + s2);
	    System.out.println(reduce.get());
	    
	    boolean allMatch = list.stream().allMatch((s)->s.startsWith("a"));
	    System.out.println(allMatch);
	    boolean anyMatch = list.stream().anyMatch((s)->s.startsWith("a"));
	    System.out.println(anyMatch);
	    boolean noneMatch = list.stream().noneMatch((s)->s.startsWith("a"));
	    System.out.println(noneMatch);
	    
	    list.removeIf((s)->s.startsWith("a"));
	    System.out.println(list);
    }
	
	@Test
    public void testNewMap() throws Exception {
	    Map<Integer, String> map = new HashMap<Integer, String>();
	    
	    map.putIfAbsent(1, "aa");
	    map.putIfAbsent(1, "bb");
	    map.put(1, "cc");
	    System.out.println(map);
	    
	    map.computeIfAbsent(2, key -> "ffff" + key);
	    System.out.println(map);
	    
	    map.put(3, "dd");
	    map.computeIfPresent(3, (key, val)-> key + val);
	    System.out.println(map);
	    
	    map.merge(3, "_new", (val, new_val)->val.concat(new_val));
	    System.out.println(map);
	    
    }

	@Test
    public void testDate() throws Exception {
		Instant instant = Instant.now();
		System.out.println(instant.toString());

		Instant ofEpochMilli = Instant.ofEpochMilli(new Date().getTime());
		System.out.println(ofEpochMilli.toString());

		Instant parse = Instant.parse(ofEpochMilli.toString());
		parse = parse.plus(Duration.ofHours(1).plusMinutes(20));

		System.out.println(parse.toString());

//		DateTimeFormatter 
		long between = ChronoUnit.HOURS.between(ofEpochMilli, parse);
		System.out.println(between);
		long betweens = ChronoUnit.MINUTES.between(ofEpochMilli, parse);
		System.out.println(betweens);

		LocalDate now = LocalDate.now();
		System.out.println(now);
		LocalTime now2 = LocalTime.now();
		System.out.println(now2);

		LocalDateTime now3 = LocalDateTime.now();
		System.out.println(now3);
    }
	
}
