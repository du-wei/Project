package com.webapp.utils.base;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.webapp.utils.codec.KeysUtils;

public class MultiThread {

	private static String start(String data) {

//		ReentrantLock

		return KeysUtils.md5Hex(data);
	}

	@Test
	public void thread() throws Exception {
		for(int i=0; i<500; i++){
			final int ok = i;
			new Thread(new Runnable() {
				public void run() {
					String data = "hello" + ok;
					System.out.println(data + " - " + start(data) + "-" + ok);
				}
			}).start();
		}

		Thread.sleep(2000);
	}

}
