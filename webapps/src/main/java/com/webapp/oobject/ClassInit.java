package com.webapp.oobject;

public class ClassInit {

	// 静态变量
	public static String staticField = "静态变量";
	// 变量
	public String field = "变量";

	// 静态初始化块
	static {
		System.out.println(staticField);
		System.out.println("静态初始化块");
	}

	// 初始化块
	{
		System.out.println(field);
		System.out.println("初始化块");
	}

	// 构造器
	public ClassInit() {
		System.out.println("构造器");
	}

	public static void main(String[] args) {
		System.out.println("=======类初始化顺序==========");
		new ClassInit();
		System.out.println("=======继承的初始化顺序==========");
		new SubClass();
	}
}

class Parent {
	// 静态变量
	public static String p_StaticField = "父类--静态变量";
	// 变量
	public String p_Field = "父类--变量";

	// 静态初始化块
	static {
		System.out.println(p_StaticField);
		System.out.println("父类--静态初始化块");
	}

	// 初始化块
	{
		System.out.println(p_Field);
		System.out.println("父类--初始化块");
	}

	// 构造器
	public Parent() {
		System.out.println("父类--构造器");
	}
}

class SubClass extends Parent {
	// 静态变量
	public static String s_StaticField = "子类--静态变量";
	// 变量
	public String s_Field = "子类--变量";
	// 静态初始化块
	static {
		System.out.println(s_StaticField);
		System.out.println("子类--静态初始化块");
	}
	// 初始化块
	{
		System.out.println(s_Field);
		System.out.println("子类--初始化块");
	}

	// 构造器
	public SubClass() {
		System.out.println("子类--构造器");
	}

}
