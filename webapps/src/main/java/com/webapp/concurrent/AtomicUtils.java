package com.webapp.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.webapp.utils.test.ThreadUtils;

public class AtomicUtils implements Runnable {

	private static AtomicInteger atomic = new AtomicInteger();

	public static int getNextInt() {
//		atomic.decrementAndGet();
		return 1;
	}

	@Test
	public void main() {
		ThreadUtils.testSimpleCase(new AtomicUtils(), 50);
	}

	public static void main(String[] args) {
		ThreadUtils.testSimpleCase(new AtomicUtils(), 50);
	}

	@Override
	public void run() {
		atomic.incrementAndGet();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(atomic.decrementAndGet());
	}

}
