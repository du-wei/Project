package com.webapp.design.flyweight;

import java.util.HashMap;
import java.util.Map;

/** @ClassName: Flyweight.java 享元模式
 * @Package com.webapp.flyweight
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午9:58:55
 * @version V1.0 */
public class Flyweight {

	public static void main(String[] args) {
		FlyweightFactory factory = new FlyweightFactory();
		IUserManage userManage = factory.getUserManage("ok");
		System.out.println(userManage.createUser());
	}

}

// 抽象工厂
class FlyweightFactory {
	Map<String, IUserManage> userMap = new HashMap<String, IUserManage>();

	public IUserManage getUserManage(String flag) {
		IUserManage userManage = userMap.get(flag);
		if (userManage == null) {
			userManage = new UserImpl();
			userMap.put(flag, userManage);
		}
		return userManage;
	}
}

// 抽象享元
interface IUserManage {
	String createUser();
}

// 具体享元
class UserImpl implements IUserManage {

	@Override
	public String createUser() {
		return "sss";
	}

}
