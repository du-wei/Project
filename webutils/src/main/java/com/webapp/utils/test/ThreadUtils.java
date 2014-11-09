package com.webapp.utils.test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ThreadUtils {

	static Logger logger = LogManager.getLogger(ThreadUtils.class);
	AtomicInteger threadCount = new AtomicInteger();
	ExecutorService service = Executors.newCachedThreadPool();
	CompletionService<Integer> completionService = new ExecutorCompletionService<>(service);

	//多个线程运行多个实例，每个线程运行一个实例
	public static <T extends Runnable> void testMultiCase(Supplier<T> run, int count, boolean isNanoTime){
		long startTime = isNanoTime ? System.nanoTime() : System.currentTimeMillis();

		for(int i=0; i<count; i++){
			T t = run.get();
//			Thread thread = new Thread(t, "Thread-" + i);
			completionService.submit(t, i);
		}

		for(int i=0; i<count; i++){
			try {
				completionService.take().get();
			} catch (Exception e) {
				logger.error("获取线程结果出错", e);
			}
		}
		computeTime(startTime, isNanoTime);
	}

	//多个线程运行一个实例
	public static <T extends Runnable> void testSimpleCase(T run, int count, boolean isNanoTime) {
		long startTime = isNanoTime ? System.nanoTime() : System.currentTimeMillis();

		for(int i=0; i<count; i++){
//			Thread thread = new Thread(run, "Thread-" + i);
//			System.out.printf("Object-%s -> thread id[%s] name[%s]", run.hashCode(), thread.getId(), thread.getName());
			completionService.submit(run, i);
		}

		for(int i=0; i<count; i++){
			try {
				completionService.take().get();
			} catch (Exception e) {
				logger.error("获取线程结果出错", e);
			}
		}
		computeTime(startTime, isNanoTime);
	}

	public static <T extends Runnable> void testMultiCase(Supplier<T> run, int count){
		testMultiCase(run, count, false);
	}
	public static <T extends Runnable> void testSimpleCase(T run, int count) {
		testSimpleCase(run, count, false);
	}

	
	public static void testCAP(Consumer<Integer> cap, int loop) {
    	long startTime = System.nanoTime();
    	for(int i=0; i<loop; i++){
    		cap.accept(i);
    	}
    	ThreadUtils.computeTime(startTime, true);
    }
	
	public static void testSimpleCAP(Consumer<Integer> cap, int loop) {
    	long startTime = System.nanoTime();
    	cap.accept(loop);
    	ThreadUtils.computeTime(startTime, true);
    }
	
	public static void computeTime(long startTime, boolean isNanoTime){
		if(isNanoTime && String.valueOf(startTime).length() != 15){
			System.out.println(startTime + "不是正确的纳秒数");
			return;
		}
		long endTime = isNanoTime ? System.nanoTime() : System.currentTimeMillis();
		long total = endTime-startTime;

		if(isNanoTime){
//			System.out.printf("\ntotal time = %s纳秒\t", total);
			System.out.printf("\ntotal time = %s微秒\t", total/1000L);
			System.out.printf("\ntotal time = %s毫秒\t", total/1000_000L);
			System.out.printf("\ntotal time = %s秒\t", total/1000_000_000L);
			System.out.printf("\ntotal time = %s分\t", total/(1000_000_000L * 60));
		}else {                
			System.out.printf("\ntotal time = %s毫秒\t", total);
			System.out.printf("\ntotal time = %s秒\t", total/1000);
			System.out.printf("\ntotal time = %s分\t", total/1000/60);
		}
		System.out.println();
	}

	public static void computeTime(long startTime){
		computeTime(startTime, false);
	}

	public static void logCyclicBarrier(CyclicBarrier cb, String place) {
		int arrive = cb.getNumberWaiting() + 1;

		String name = Thread.currentThread().getName();
		System.out.printf("%s已经到达集合点%s, 当前有%s个已经到达\t", name, place, arrive);
	}

	public static void logAcquire(Semaphore sp) throws InterruptedException {
		System.out.printf("tryAcquire=s, availablePermits=%s, queueLength=%s, queuedThreads=%s, isFair=%s\t",
				 sp.availablePermits(), sp.getQueueLength(),
				sp.hasQueuedThreads(), sp.isFair());
		sp.acquire();
		System.out.printf("线程 %s进入，当前有%d个并发\t", Thread.currentThread().getName(), threadCount.incrementAndGet());
	}

	public static void logRelease(Semaphore sp) {
		sp.release();
		System.out.printf("线程 %s离开，当前有%d个并发\t", Thread.currentThread().getName(), threadCount.decrementAndGet());
	}
}
