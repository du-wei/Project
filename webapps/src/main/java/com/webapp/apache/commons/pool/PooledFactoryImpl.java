package com.webapp.apache.commons.pool;

import java.lang.reflect.Method;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class PooledFactoryImpl<V> extends BasePooledObjectFactory<V> {

	private int count = 0;

	@Override
	public V create() throws Exception {
		String ok = "Str" + count++;
		System.out.println("create run + " + ok);
		return (V) ok;
	}

	@Override
	public PooledObject<V> wrap(V value) {
		System.out.println("wrap..." + value);
		return new DefaultPooledObject<V>(value);
	}

	@Override
	public PooledObject<V> makeObject() throws Exception {
		System.out.println("makeObject...");
		return super.makeObject();
	}

	@Override
	public void destroyObject(PooledObject<V> p) throws Exception {
		System.out.println("destroyObject...");
		super.destroyObject(p);
	}

	@Override
	public boolean validateObject(PooledObject<V> p) {
		System.out.println("validateObject...");
		return super.validateObject(p);
	}

	@Override
	public void activateObject(PooledObject<V> p) throws Exception {
		System.out.println("activateObject...");
		super.activateObject(p);
	}

	@Override
	public void passivateObject(PooledObject<V> p) throws Exception {
		System.out.println("passivateObject...");
		super.passivateObject(p);
	}

}
