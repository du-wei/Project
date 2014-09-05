package com.webapp.utils.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ThreadPoolUtils {

	private static Logger logger = Logger.getLogger(ThreadPoolUtils.class);

	@Test
	public void testName() throws Exception {

	}

	public <T> List<FutureTask<T>> execute(ExecutorService executor,
			Callable<T> callable, int loopSize) {
		List<FutureTask<T>> futureTasks = new ArrayList<>();
		for (int i = 0; i < loopSize; i++) {
			FutureTask<T> task = new FutureTask<T>(callable);
			executor.submit(task);
			futureTasks.add(task);
		}
		return futureTasks;
	}

	public <T> List<T> submitResult(ExecutorService executor,
			Callable<T> callable, int loopSize) {
		List<T> result = new ArrayList<>();

		List<Future<T>> futures = submit4Future(executor, callable, loopSize);
		try {
			for (Future<T> future : futures) {
				result.add(future.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error("获取结果出错！", e);
		}
		return result;
	}

	public <T> List<Future<T>> submit4Future(ExecutorService executor,
			Callable<T> callable, int loopSize) {
		List<Future<T>> futures = new ArrayList<>();
		for (int i = 0; i < loopSize; i++) {
			Future<T> future = executor.submit(callable);
			futures.add(future);
		}
		return futures;
	}

	public List<Future<?>> submit4Future(ExecutorService executor,
			Runnable runnable, int loopSize) {
		List<Future<?>> futures = new ArrayList<>();
		for (int i = 0; i < loopSize; i++) {
			Future<?> future = executor.submit(runnable);
			futures.add(future);
		}
		return futures;
	}

	public <T> List<Future<T>> submit4Future(ExecutorService executor,
			Runnable runnable, int loopSize, T result) {
		List<Future<T>> futures = new ArrayList<>();
		for (int i = 0; i < loopSize; i++) {
			Future<T> future = executor.submit(runnable, result);
			futures.add(future);
		}
		return futures;
	}

	public <T> CompletionService<T> submit4Complete(ExecutorService executor,
			Callable<T> callable, int loopSize) {
		CompletionService<T> cs = new ExecutorCompletionService<>(executor);
		for (int i = 0; i < loopSize; i++) {
			cs.submit(callable);
		}
		return cs;
	}

	public <T> CompletionService<T> submit4Complete(ExecutorService executor,
			Runnable runnable, int loopSize, T result) {
		CompletionService<T> cs = new ExecutorCompletionService<>(executor);
		for (int i = 0; i < loopSize; i++) {
			cs.submit(runnable, result);
		}
		return cs;
	}

	public static ExecutorService newThreadPool(int minPoolSize, int maxPoolSize) {
		return newThreadPool(minPoolSize, maxPoolSize, 0);
	}

	public static ExecutorService newThreadPool(int minPoolSize,
			int maxPoolSize, int keepAliveTime) {
		return newThreadPool(minPoolSize, maxPoolSize, keepAliveTime,
				new LinkedBlockingQueue<Runnable>());
	}

	public static ExecutorService newThreadPool(int minPoolSize,
			int maxPoolSize, int keepAliveTime,
			BlockingQueue<Runnable> workQueue) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(minPoolSize,
				maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue);
		executor.setCorePoolSize(minPoolSize);

		/*
		 * 排队有三种通用策略 直接提交 默认选项是 SynchronousQueue 无界队列 使用无限的LinkedBlockingQueue
		 * 有界队列 有限的 maximumPoolSizes->ArrayBlockingQueue
		 */
		executor.setMaximumPoolSize(maxPoolSize);
		executor.setKeepAliveTime(keepAliveTime, TimeUnit.MILLISECONDS);
		executor.allowCoreThreadTimeOut(false);
		executor.setThreadFactory(Executors.defaultThreadFactory());

		/*
		 * 被拒绝的任务处理策略 1.AbortPolicy -> 默认处理程序遭到拒绝将抛出运行时
		 * RejectedExecutionException。 2.CallerRunsPolicy -> 线程调用运行该任务的
		 * execute本身。此策略提供简单的反馈控制机制，能够减缓新任务的提交速度。 3.DiscardPolicy ->
		 * 不能执行的任务将被删除。 4.DiscardOldestPolicy ->
		 * 如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）。
		 */
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		return executor;
	}

	public class ThreadPoolExecutorUtils extends ThreadPoolExecutor {

		public ThreadPoolExecutorUtils(int corePoolSize, int maximumPoolSize,
				long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}

		@Override
		public void execute(Runnable command) {
			super.execute(command);
		}

		@Override
		protected void beforeExecute(Thread t, Runnable r) {
			logger.info("beforeExecute");
			super.beforeExecute(t, r);
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			logger.info("afterExecute");
			super.afterExecute(r, t);
		}

	}

	// 创建一个定长的线程池，每提交一个任务就创建一个线程，知道最大。当某个线程由于Exception而结束， 会创建一个新的
	public static ExecutorService newFixedThreadPool() {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		return threadPool;
	}

	// 创建一个可缓存的线程池，如果线程池的长度超过需求时，回收空闲的。 如果需求增加时，可以创建新的
	public static ExecutorService newCachedThreadPool() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		return threadPool;
	}

	public static ExecutorService newSingleThreadExecutor() {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		return threadPool;
	}

	public static ScheduledExecutorService newScheduledThreadPool() {
		ScheduledExecutorService threadPool = Executors
				.newScheduledThreadPool(10);
		return threadPool;
	}

	public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
		ScheduledExecutorService threadPool = Executors
				.newSingleThreadScheduledExecutor();
		return threadPool;
	}

}
