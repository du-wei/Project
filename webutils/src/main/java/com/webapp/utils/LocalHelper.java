package com.webapp.utils;


public final class LocalHelper {

	public final static <T> T of(ThreadLocal<T> local, T utils) {
		if (local.get() == null) local.set(utils);
		return local.get();
    }
	
}
