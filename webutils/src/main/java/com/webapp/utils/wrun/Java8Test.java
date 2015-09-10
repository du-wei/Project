package com.webapp.utils.wrun;

import java.lang.annotation.Repeatable;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

//1 interface
interface Inter {
	int total(int a);
	default int count(int b){
		return 10+b;
	}
}

//3
@FunctionalInterface
interface Converter<P, T> {
    T convert(P para);
}

//4
class Something {
	String startsWith(String s) {
		return String.valueOf(s.charAt(0));
	}
}
class Person {
	String firstName;
	String lastName;
	Person() {}
	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
interface PersonFactory<P>{
	 P create(String firstName, String lastName);
}

public class Java8Test {
	public static void main(String[] args) {
//		java1();
//	    java2();
//    	
    	java3();
//		
//		java4();
//		
//		java6();
//		
//		java7();
//		
//		java9();
    }

	private static void java9() {
	    Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);   // legacy java.util.Date
		
		System.out.println(ZoneId.getAvailableZoneIds());
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
		
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		System.out.println(now1.isBefore(now2));  // false
		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
		System.out.println(hoursBetween);       // -3
		System.out.println(minutesBetween);     // -239
		
		
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late); // 23:59:59
		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(
		        Locale.GERMAN);
		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime); // 13:37
		
		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println(dayOfWeek);    // FRIDAY
		
		LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
		DayOfWeek dayOfWeek1 = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek1);      // WEDNESDAY
		Month month = sylvester.getMonth();
		System.out.println(month);          // DECEMBER
		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay);    // 1439
    }

	private static void java7() {
	    //7
		List<String> list = new ArrayList<String>();
		
		list.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
		list.forEach((s) -> s.startsWith("a"));
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		long t0 = System.nanoTime();
		long count = values.stream().sorted().count();
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(millis);
		
		count = values.parallelStream().sorted().count();
		long t2 = System.nanoTime();
		millis = TimeUnit.NANOSECONDS.toMillis(t2 - t1);
		System.out.println(millis);
		
		Arrays.stream(new int[]{}).count();
		Arrays.parallelSort(new int[]{});
		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
    }

	private static void java6() {
	    //6
		Predicate<String> predicate = (s) -> s.length() > 0;
		System.out.println(predicate.test("hello"));
		System.out.println(predicate.negate().test("hello"));
		Predicate<String> isEmpty = String::isEmpty;
		
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		backToString.apply("123");     // "123"
		
		Optional<String> optional = Optional.of("bam");
		optional.isPresent();           // true
		optional.get();                 // "bam"
		optional.orElse("fallback");    // "bam"
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));
    }

	private static void java4() {
	    //4
		Something something = new Something();
		Converter<String, String> converter2 = something::startsWith;
		String converted2 = converter2.convert("Java");
		System.out.println(converted2);
		
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
    }

	private static void java3() {
	    //3
    	Converter<String, Integer> old = new Converter<String, Integer>() {
			@Override
            public Integer convert(String para) {
	            return 2;
            }
		};
    	Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
//    	Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted);
		
		int num = 10;
//		num = 11;
    	Converter<Integer, Integer> converter1 = (from) -> Integer.valueOf(from + num);
    	
    }

	private static void java2() {
	    //2
	    List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
    	Collections.sort(names, new Comparator<String>() {
			@Override
            public int compare(String o1, String o2) {
	            return 0;
            }
    	});
    	Collections.sort(names, (String a, String b) -> {
		    return b.compareTo(a);
		});
    	Collections.sort(names, (String a, String b) -> b.compareTo(a));
    	Collections.sort(names, (a, b) -> b.compareTo(a));
    }

	private static void java1() {
	    //1
	    Inter inter = new Inter() {
            public int total(int a) {
	            return 100;
            }
	    };
	    System.out.println(inter.count(10));
	    System.out.println(inter.total(100));
    }
}



@interface Hints {
	Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
	String value();
}

@Hints({ @Hint("hint1"), @Hint("hint2") })
class Persons {
}

@Hint("hint1")
@Hint("hint2")
class Personx {
}
