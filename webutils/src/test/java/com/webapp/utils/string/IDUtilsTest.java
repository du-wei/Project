package com.webapp.utils.string;
/**
 * idchecker测试
 * @author rongxinhua
 *
 */
public class IDUtilsTest {

	public static void main(String[] args) {
		String num = "13082519880524101X";
		IDUtils checker = IDUtils.of(num);
		System.out.println("出生年月日  : " + checker.getBirth());
		System.out.println("性别  ：" + checker.getSex());
		System.out.println("居民地址  : " + checker.getAddr());
		System.out.println("身份证号码是否合法 : " + checker.checkAll() + "  " + checker.getErrorMsg());
		
		for(String msg : checker.getErrorMsgs()) {
			System.out.print(msg + " | ");
		}
	}

}
