package com.webapp.design;

public class DesignUtils {

	public static void main(String[] args) {
		Singleton ok = null;
		DesignUtils.newInstance(Singleton.class, ok);
	}

	public static <T> T newInstance(Class<T> clz, T t) {
		if (t == null) {
			synchronized (DesignUtils.class) {
				if (t == null) {
					try {
						t = clz.newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

}
