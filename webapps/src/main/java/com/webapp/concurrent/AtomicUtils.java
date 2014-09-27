package com.webapp.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.webapp.utils.test.ThreadUtils;

public class AtomicUtils implements Runnable {

	private static AtomicInteger atomic = new AtomicInteger();

	public static int getNextInt() {
		return atomic.incrementAndGet();
	}

	@Test
	public void main() {
		ThreadUtils.testThread(() -> new AtomicUtils(), 50, true);
	}

	@Override
	public void run() {
		System.out.println(getNextInt());
	}

}
