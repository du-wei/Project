package com.webapp.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import com.webapp.utils.math.MathUtils;

public class ConcurrentUtils {

	public static void main(String[] args) throws Exception {
		testCyclicBarrier();
	}

	private static void testCyclicBarrier() {
		ExecutorService exec = Executors.newCachedThreadPool();

		final CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
			@Override
			public void run() {
				System.out.println("大家都到齐了，开始happy去");
			}
		});

		for (int i = 0; i < 4; i++) {
			exec.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(MathUtils.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()
							+ "到了，其他哥们呢");
					try {
						barrier.await();// 等待其他哥们
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		exec.shutdown();
	}

	private static void testCountDownLatch() throws InterruptedException {
		ExecutorService executor = Executors.newCachedThreadPool();
		final CountDownLatch doneSignal = new CountDownLatch(10);
		final CountDownLatch startSignal = new CountDownLatch(1);// 开始执行信号

		for (int i = 1; i <= 10; i++) {
			System.out.println("---------------" + i);

			final int index = i;
			// new Thread(new Worker(i, doneSignal, startSignal)).start();
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						startSignal.await(); // 等待开始执行信号的发布
						System.out.println(index + " finish");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						doneSignal.countDown();
					}
				}
			});
		}
		System.out.println("begin------------");
		startSignal.countDown();// 开始执行啦
		doneSignal.await();// 等待所有的线程执行完毕
		System.out.println("Ok");
	}

	public static void testSemaphore() throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		final Semaphore semp = new Semaphore(3, true);
		for (int i = 0; i < 20; i++) {
			final int index = i;
			Thread.sleep(20);
			executor.execute(new Runnable() {
				public void run() {
					try {
						semp.acquire();
						System.out.println("Accessing   " + index);
						Thread.sleep(1000);
						semp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executor.shutdown();
	}

}
