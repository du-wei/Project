package com.webapp.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		lock.lock();
		System.out.println("hello world");
		lock.unlock();

		ReadWriteLock rwl = new ReentrantReadWriteLock();
		rwl.readLock().lock();

		rwl.readLock().unlock();

		rwl.writeLock().lock();
		rwl.writeLock().unlock();
	}

	/** Prevent instantiation */
	private LockTest() {
	}

}
