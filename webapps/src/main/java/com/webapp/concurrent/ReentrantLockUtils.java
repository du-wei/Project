package com.webapp.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class ReentrantLockUtils<T> {

	private static Logger log = Logger.getLogger(ReentrantLockUtils.class.getName());

	// 独占锁
	Lock lock = new ReentrantLock();
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
			log.info(String.format("take-count-%d-put-%d-%s", count, take_index, data));
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
			log.info(String.format("take-count-%d-take-%d-%s", count, take_index, data));
			if (++take_index == items.length) take_index = 0;
			--count;
			full.signal();
			return (T) data;
		} finally {
			lock.unlock();
		}
	}
}
