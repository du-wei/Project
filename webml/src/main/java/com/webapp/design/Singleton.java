package com.webapp.design;


/** @ClassName: Singleton.java 单例模式
 * @Package com.webapp.design
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午9:25:22
 * @version V1.0 */
//单例模式-懒汉式单例 比下面的有所改进
public class Singleton {

	// 私有静态对象,加载时候不做初始化
	private static Singleton instance = null;

	// 私有构造方法,避免外部创建实例
	private Singleton() {
		System.out.println(Singleton.class.getName() + " instance");
	}

	// 静态工厂方法,返回此类的唯一实例.当发现实例没有初始化的时候,才初始化.
	public static Singleton getInstance() {
//		if (instance == null) {
			synchronized (Singleton.class) {
				 if(instance == null){
					 instance = new Singleton();
				 }
			}
//		}
		return instance;
	}

}

// 单例模式-饿汉式单例
class EagerSingleton {

	// 私有的(private)唯一(static final)实例成员,在类加载的时候就创建好了单例对象
	private static final EagerSingleton instance = new EagerSingleton();

	// 私有构造方法,避免外部创建实例
	private EagerSingleton() {
		System.out.println(EagerSingleton.class.getName() + " instance");
	}

	// 静态工厂方法,返回此类的唯一实例.
	public static EagerSingleton getInstance() {
		return instance;
	}
}

// 单例模式-优化式单例 最好改进
class GoodSingleton {

	// 私有构造方法,避免外部创建实例
	private GoodSingleton() {
		System.out.println(GoodSingleton.class.getName() + " instance");
	}

	private static class SingleHolder {
		private static GoodSingleton instance = new GoodSingleton();
	}

	// 静态工厂方法,返回此类的唯一实例.当发现实例没有初始化的时候,才初始化.
	public static GoodSingleton getInstance() {
		return SingleHolder.instance;
	}
	
	//序列化时也单例
//	private Object readResolve(){
//		return SingleHolder.instance;
//	}
}
