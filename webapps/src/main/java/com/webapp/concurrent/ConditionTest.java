package com.webapp.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

	public static void main(String[] args) throws Exception {
		final ConditionTest obj = new ConditionTest();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			new Thread(new Runnable() {
				public void run() {
					obj.put(index);
					System.out.println("put +++++++ ");
				}
			}).start();
		}

		for (int i = 0; i < 10; i++) {
			// int index = i;
			new Thread(new Runnable() {
				public void run() {
					System.out.println(obj.take() + "take ---------- ");
				}
			}).start();
		}
	}

	Lock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();
	Condition notEmpty = lock.newCondition();

	Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) {
		lock.lock();
		try {
			while (count > items.length) {
				try {
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() {
		lock.lock();
		try {
			while (count <= 0) {
				try {
					notEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}
