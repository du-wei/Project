package com.webapp.test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyTest {

	public static void main(String[] args) throws Exception {
		long a = System.currentTimeMillis();
		System.out.println(a);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkJoinTask<Integer> forkJoinTask = forkJoinPool
				.submit(new Calculator(0, 500000));
		System.out.println(forkJoinTask.get());
		long b = System.currentTimeMillis();
		System.out.println(b);
		System.out.println(b - a);

		long c = System.currentTimeMillis();
		System.out.println(c);
		int sum = 0;
		for (int i = 0; i < 500000; i++) {
			sum += i;
		}
		System.out.println(sum);
		long d = System.currentTimeMillis();
		System.out.println(d);
		System.out.println(d - c);

	}

	static class Calculator extends RecursiveTask<Integer> {

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

}
