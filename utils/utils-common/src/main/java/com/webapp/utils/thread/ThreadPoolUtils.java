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
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPoolUtils {

	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolUtils.class);

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
		 * 被拒绝的任务处理策略 
		 * 1.AbortPolicy -> 默认处理程序遭到拒绝将抛出运行时RejectedExecutionException。 
		 * 2.CallerRunsPolicy -> 线程调用运行该任务的execute本身。此策略提供简单的反馈控制机制，能够减缓新任务的提交速度。
		 * 3.DiscardPolicy -> 不能执行的任务将被删除。 
		 * 4.DiscardOldestPolicy -> 如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）。
		 */
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		return executor;
	}

}
