package com.webapp.design.observe;

/** @ClassName: MyTest.java 观察者模式
 * @Package com.webapp.design.observe
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午10:29:11
 * @version V1.0 */
public class MyTest {

	public static void main(String[] args) {

		myObserver();
		systemObserver();
	}

	private static void myObserver() {
		MySubjectImpl data = new MySubjectImpl();

		data.addObserver(new MyObserverImpl());
		data.addListener(new MyObserver() {
			@Override
			public void update(String msg) {
				System.out.println(msg);
			}
		});

		data.changed("天气数据来了");
	}

	public static void systemObserver() {
		Subject sub = new Subject();
		Subscribe observer = new Subscribe();

		sub.addObserver(observer);
		sub.addObserver(new Subscribe());

		sub.changed("ok");
		// sub.notifyObservers("msg");
	}

}
