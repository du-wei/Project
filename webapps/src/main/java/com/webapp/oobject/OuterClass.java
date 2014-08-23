package com.webapp.oobject;

public class OuterClass {

	public static void main(String[] args) {

		OuterClass.Inner inner = new OuterClass().new Inner();
		inner.show();

		OuterClass.StaticInner staticInner = new OuterClass.StaticInner();
		staticInner.print();

		OuterClass.StaticInner.show();
	}

	class Inner {
		void show() {
			System.out.println(" inner -> method");
		}
	}

	static class StaticInner {
		static void show() {
			System.out.println(" static inner -> static method");
		}

		void print() {
			System.out.println(" static inner -> method");
		}
	}
}
