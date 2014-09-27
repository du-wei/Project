package com.webapp.utils.test;

import java.util.function.Supplier;

public interface ThreadUtils {

	public static <T extends Runnable> void testThread(Supplier<T> run, int count, boolean isNanoTime){
		long startTime = isNanoTime ? System.nanoTime() : System.currentTimeMillis();

		for(int i=0; i<count; i++){
			T t = run.get();
			Thread thread = new Thread(t, "Thread-" + i);
			thread.start();
		}

		computeTime(startTime, isNanoTime);
	}

	public static void testThread(Runnable run, int count, boolean isNanoTime) {
		long startTime = isNanoTime ? System.nanoTime() : System.currentTimeMillis();

		for(int i=0; i<count; i++){
			Thread thread = new Thread(run, "Thread-" + i);
			thread.start();
		}

		computeTime(startTime, isNanoTime);
	}

	public static void computeTime(long startTime, boolean isNanoTime){
		if(isNanoTime && String.valueOf(startTime).length() != 16){
			System.out.println(startTime + "不是正确的纳秒数");
			return;
		}
		long endTime = isNanoTime ? System.nanoTime() : System.currentTimeMillis();
		long total = endTime-startTime;

		if(isNanoTime){
			Console.out("total time = %d纳秒", total);
			Console.out("total time = %d微秒", total/1000L);
			Console.out("total time = %d毫秒", total/1000_000L);
			Console.out("total time = %d秒", total/1000_000_000L);
			Console.out("total time = %d分", total/(1000_000_000L * 60));
		}else {
			Console.out("total time = %d毫秒", total);
			Console.out("total time = %d秒", total/1000);
			Console.out("total time = %d分", total/1000*60);
		}
	}

	public static <T extends Runnable> void testThread(Supplier<T> run, int count){
		testThread(run, count, false);
	}
	public static void testThread(Runnable run, int count) {
		testThread(run, count, false);
	}

	public static void computeTime(long startTime){
		computeTime(startTime, false);
	}

}
