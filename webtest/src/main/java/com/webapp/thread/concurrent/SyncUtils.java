package com.webapp.thread.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

import jodd.util.ThreadUtil;

import org.junit.Test;

import com.webapp.utils.random.RandomUtils;
import com.webapp.utils.wrun.ThreadUtils;

public class SyncUtils {

	@Test
	public void testPhaser() throws Exception {
		Phaser phaser = new Phaser(3);
		ThreadUtils.testSimpleCase(() -> {
			ThreadUtil.sleep(RandomUtils.nextInt(0, 5) * 1000);

			// int arrive = phaser.arrive();
			// int aaaa = phaser.arriveAndAwaitAdvance();
			// int aad = phaser.arriveAndDeregister();

			    // int awaitAdvance = phaser.awaitAdvance(1);
			    // int aai = phaser.awaitAdvanceInterruptibly(1);
			    // int bulkRegister = phaser.bulkRegister(1);
			    // int register = phaser.register();

			    int phase = phaser.getPhase();
			    // int registeredParties = phaser.getRegisteredParties();
			    System.out.println(phase);
			    int awaitAdvance = phaser.awaitAdvance(0);

			    // int arrive = phaser.arrive();
			    int arrivedParties = phaser.getArrivedParties();
			    int unarrivedParties = phaser.getUnarrivedParties();

			    System.out.printf("%s-%s-%s-%s", 0, phase, arrivedParties,
			            unarrivedParties);
			    // System.out.println(Thread.currentThread().getName());
			    // phaser.arrive(); //此处可使用latch.countDown()
			    //
			    // if(phaser.getArrivedParties() > 0){
			    // try {
			    // phaser.awaitAdvance(phaser.getPhase()); //此处可使用latch.await()
			    // } catch (Exception e) {
			    // e.printStackTrace();
			    // }
			    // }
			    // System.out.println(Thread.currentThread().getName());
		    }, 3);
	}

	@Test
	public void testExchanger() {
		// 线程之间数据交换
		Exchanger<String> exchanger = new Exchanger<String>();
		ThreadUtils.testSimpleCase(() -> {
			try {

				String data = RandomUtils.str(5);
				System.out.printf("%s要交换数据%s",
				        Thread.currentThread().getName(), data);
				String changeData = exchanger.exchange(data);
				System.out.printf("%s换回的数据%s",
				        Thread.currentThread().getName(), changeData);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}, 2);
	}

	@Test
	public void testCyclicBarrier() {
		// 控制多个线程共同到达某几个点
		CyclicBarrier cb = new CyclicBarrier(3,
		        () -> System.out.println("都到齐了，继续走啊"));
		ThreadUtils.testSimpleCase(() -> {
			try {
				ThreadUtil.sleep(RandomUtils.nextInt(0, 5) * 1000);
				ThreadUtils.logCyclicBarrier(cb, "A");
				cb.await();

				ThreadUtil.sleep(RandomUtils.nextInt(0, 5) * 1000);
				ThreadUtils.logCyclicBarrier(cb, "B");
				cb.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, 3);
	}

	@Test
	public void testCountDownLatch() {
		// 同步计数器 等待所有线程一起执行
		CountDownLatch latch = new CountDownLatch(3);
		ThreadUtils.testSimpleCase(() -> {
			System.out.println(Thread.currentThread().getName());
			latch.countDown();
			if (latch.getCount() > 0) {
				try {
					latch.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName());
		}, 3);

	}

	@Test
	public void testSemaphore() {
		// 信号灯 控制线程的并发数
		Semaphore sp = new Semaphore(3);
		ThreadUtils.testSimpleCase(() -> {
			try {
				// sp.acquire();
				ThreadUtils.logAcquire(sp);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			ThreadUtil.sleep(1000);

			// sp.release();
			ThreadUtils.logRelease(sp);
		}, 5);
	}

}
