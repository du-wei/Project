package com.webapp.utils.test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;

public class JavaDate {

	@Test
    public void testName() throws Exception {

		Instant instant = Instant.now();
		System.out.println(instant.toString());

		Instant ofEpochMilli = Instant.ofEpochMilli(new Date().getTime());
		System.out.println(ofEpochMilli.toString());

		Instant parse = Instant.parse(ofEpochMilli.toString());
		parse = parse.plus(Duration.ofHours(1).plusMinutes(20));

		System.out.println(parse.toString());


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
