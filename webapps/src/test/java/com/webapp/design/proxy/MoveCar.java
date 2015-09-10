/** @Title: Car.java
 * @Package com.webapp.design.proxy
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-21 上午11:06:49
 * @version V1.0 */
package com.webapp.design.proxy;

/** @ClassName: Car.java
 * @Package com.webapp.design.proxy
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-21 上午11:06:49
 * @version V1.0 */
public class MoveCar implements Moveable {

	@Override
	public void move() {
		System.out.println("Car Moving");
	}

}
