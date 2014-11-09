package com.webapp.utils.test;


public class Instance {
	
	private int i;
	private Instance(int i){
		this.i = i;
	}
	
	private static final ThreadLocal<Instance> local = new ThreadLocal<Instance>();
	public static Instance of(int i) {
		if (local.get() == null) {
		    synchronized (Instance.class) {
		    	local.set(new Instance(i));
		    }
		}
		return local.get();
    }
	public String toStr() {
	    return String.valueOf(local.get().i);
	}
	
//	private int i = 0;
//	private Instance(int i){
//		this.i = i;
//	}
//	public static Instance of(int i) {
//		return new Instance(i);
//    }
//	public String toStr() {
//	    return String.valueOf(i);
//	}
	
}
