package com.webapp.design.state;

/** @ClassName: MyTest.java 状态模式
 * @Package com.webapp.design.state
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午10:29:56
 * @version V1.0 */
public class MyTest {

	public static void main(String[] args) {
		Boy boy = new Boy();

		MM mm = new MM(new MMHappyState());
		boy.doSomeThing(1);
		boy.pursue(mm);
		boy.doSomeThing(2);
		boy.pursue(mm);
	}

}
