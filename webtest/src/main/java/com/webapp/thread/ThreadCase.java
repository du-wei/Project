package com.webapp.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;

import org.junit.Test;

public class ThreadCase {

	/** Future模式 Master-Worker模式
	 *
	 *
	 * jppf hama akka */

	private static ExecutorService executor = Executors
	        .newSingleThreadExecutor();

	@Test
	public void testName() throws Exception {
		// thread();
		// runnable();
		// callable();
		// futureTask();

		forkjoin();
	}
	public static void forkjoin() throws Exception {
		@SuppressWarnings("unused")
		class FJWorkThread extends ForkJoinWorkerThread {
			@Override
			protected void onStart() {
				System.out.println("start");
				super.onStart();
			}

			@Override
			protected void onTermination(Throwable exception) {
				System.out.println("onTermination");
				super.onTermination(exception);
			}

			protected FJWorkThread(ForkJoinPool pool) {
				super(pool);
				System.out.println("FJWorkThread");
			}

		}

		ForkJoinWorkerThreadFactory fjwtf = new ForkJoinWorkerThreadFactory() {
			@Override
			public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
				return new FJWorkThread(pool);
			}
		};
		// ForkJoinPool forkJoinPool = new ForkJoinPool(2, fjwtf, null, false);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Integer> forkJoinTask = forkJoinPool
		        .submit(new Calculator(0, 500));
		Integer integer = forkJoinTask.get();
		showLog(forkJoinPool);
		System.out.println(integer);

	}

	private static void showLog(ForkJoinPool pool) {
//		getPoolSize(): 此方法返回 int 值，它是ForkJoinPool内部线程池的worker线程们的数量。
//		getParallelism(): 此方法返回池的并行的级别。
//		getActiveThreadCount(): 此方法返回当前执行任务的线程的数量。
//		getRunningThreadCount():此方法返回没有被任何同步机制阻塞的正在工作的线程。
//		getQueuedSubmissionCount(): 此方法返回已经提交给池还没有开始他们的执行的任务数。
//		getQueuedTaskCount(): 此方法返回已经提交给池已经开始他们的执行的任务数。
//		hasQueuedSubmissions(): 此方法返回 Boolean 值，表明这个池是否有queued任务还没有开始他们的执行。
//		getStealCount(): 此方法返回 long 值，worker 线程已经从另一个线程偷取到的时间数。
//		isTerminated(): 此方法返回 Boolean 值，表明 fork/join 池是否已经完成执行。
		System.out.printf("**********************\n");
		System.out.printf("Fork/Join Pool log\n");
		System.out.printf("Parallelism:%d\n", pool.getParallelism());
		System.out.printf("Pool Size:%d\n", pool.getPoolSize());
		System.out.printf("Active Thread Count:%d\n", pool.getActiveThreadCount());
		System.out.printf("Running Thread Count:%d\n",pool.getRunningThreadCount());
		System.out.printf("Queued Submission:%d\n",pool.getQueuedSubmissionCount());
		System.out.printf("Queued Tasks:%d\n", pool.getQueuedTaskCount());
		System.out.printf("Queued Submissions:%s\n", pool.hasQueuedSubmissions());
		System.out.printf("Steal Count:%d\n", pool.getStealCount());
		System.out.printf("Terminated :%s\n", pool.isTerminated());
		System.out.printf("**********************\n");
	}

}

class Calculator extends RecursiveTask<Integer> {

	/** @Fields serialVersionUID : TODO 字段说明 */
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 1000;
	private int start;
	private int end;
	Calculator left;
	Calculator right;

	public Calculator(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// 如果任务足够小，就计算任务
		if ((end - start) < THRESHOLD) {
			for (int i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			// 如果任务大于阀值，就分成两个子任务
			int middle = (start + end) / 2;

			left = new Calculator(start, middle);
			right = new Calculator(middle + 1, end);
			// 执行子任务
			left.fork();
			right.fork();
			// 等待任务完成
			// 合并
			sum = left.join() + right.join();
		}
		return sum;
	}

}

