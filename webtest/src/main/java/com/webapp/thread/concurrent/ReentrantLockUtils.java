package com.webapp.thread.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantLockUtils<T> {

	// StampedLock jj;
	// http://developer.51cto.com/art/201405/439149.htm

	private static final Logger logger = LoggerFactory.getLogger(ReentrantLockUtils.class);
	// 独占锁
	Lock lock = new ReentrantLock();
	// ReentrantReadWriteLock jj;
	Condition full = lock.newCondition();
	Condition empty = lock.newCondition();

	Object[] items = new Object[10];
	int put_index, take_index, count;

	public void put(T data) {
		lock.lock();
		try {
			while (count >= items.length) {
				try {
					full.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			items[put_index] = data;
			++count;
			logger.info(String.format("take-count-%d-put-%d-%s", count,
			        take_index, data));
			if (++put_index == items.length) put_index = 0;
			empty.signal();
		} finally {
			lock.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	public T take() {
		lock.lock();
		try {
			while (count <= 0) {
				try {
					empty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Object data = items[take_index];
			logger.info(String.format("take-count-%d-take-%d-%s", count,
			        take_index, data));
			if (++take_index == items.length) take_index = 0;
			--count;
			full.signal();
			return (T) data;
		} finally {
			lock.unlock();
		}
	}
}
