package com.webapp.design.command;

/** @ClassName: MyTest.java 命令模式
 * @Package com.webapp.design.command
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午10:27:55
 * @version V1.0 */
public class MyTest {

	public static void main(String[] args) {
		Boy boy = new Boy();

		boy.addCommand(new CommandHug());
		boy.addCommand(new CommandHug());

		MM mm = new MM();
		mm.order(boy);

		// Boy boy = new Boy();
		// boy.addCommand(new CommandHug());
		// boy.addCommand(new CommandShopping());
		// boy.executeCommands();
	}

}
