package com.webapp.apache.commons.pool;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class KeyedPooledFactoryImpl<K, V> extends
		BaseKeyedPooledObjectFactory<K, V> {

	@Override
	public V create(K key) throws Exception {
		String ok = "Str" + Math.random();
		System.out.println("create run + " + ok);
		return (V) ok;
	}

	@Override
	public PooledObject<V> wrap(V value) {
		System.out.println("wrap..." + value);
		return new DefaultPooledObject<V>(value);
	}

	@Override
	public PooledObject<V> makeObject(K key) throws Exception {
		System.out.println("makeObject...");
		return super.makeObject(key);
	}

	// 该方法用于销毁对象
	@Override
	public void destroyObject(K key, PooledObject<V> p) throws Exception {
		System.out.println("destroyObject...");
		super.destroyObject(key, p);
	}

	// 校验池化对象是否可用的方法
	@Override
	public boolean validateObject(K key, PooledObject<V> p) {
		System.out.println("validateObject...");
		return super.validateObject(key, p);
	}

	// 将对象设置为激活状态 对于可变对象，可以使用编码方式将对象转变为初始状态
	@Override
	public void activateObject(K key, PooledObject<V> p) throws Exception {
		System.out.println("activateObject...");
		super.activateObject(key, p);
	}

	// 将池化对象设置为休眠状态，表示该对象不可用
	@Override
	public void passivateObject(K key, PooledObject<V> p) throws Exception {
		System.out.println("passivateObject...");
		super.passivateObject(key, p);
	}

}
