package com.webapp.design.decorator;

/** @ClassName: MyTest.java 装饰模式
 * @Package com.webapp.design.decorator
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午10:27:34
 * @version V1.0 */
public class MyTest {

	public static void main(String[] args) {

		Dog dog = new BigDog();

		Decorator dt1 = new DecoratorColor(dog);

		Decorator dt2 = new DecoratorWeight(dt1);

		double cost = dt2.cost();
		String desc = dt2.getDesc();

		System.out.println(cost + " --> " + desc);

	}

}
