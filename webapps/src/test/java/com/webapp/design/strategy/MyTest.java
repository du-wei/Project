package com.webapp.design.strategy;

import java.util.Arrays;

/** @ClassName: MyTest.java 策略模式
 * @Package com.webapp.design.strategy
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午10:30:11
 * @version V1.0 */
public class MyTest {

	public static void main(String[] args) {

		Cat[] cats =
		{ new Cat(8, 8), new Cat(5, 2), new Cat(4, 3), new Cat(2, 4) };

//		Arrays.sort(cats);

		Arrays.parallelSort(cats);
		// Arrays.sort(cats, new CatHeightComparator());

		for (int i = 0; i < cats.length; i++) {
			System.out.println(cats[i].getHeight() + " - "
			        + cats[i].getWeight());
		}
	}

}
