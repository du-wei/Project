package com.webapp.concurrent;


public class ConditionTest {

	public static void main(String[] args) throws Exception {
		final ReentrantLockUtils<Integer> obj = new ReentrantLockUtils<>();
		for (int i = 0; i < 20; i++) {
			final int index = i;
			new Thread(()->{obj.put(index);}).start();
		}

		for (int i = 0; i < 20; i++) {
			new Thread(()->{obj.take();}).start();
		}
	}

}
