package com.webapp.program;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONPath;
import com.webapp.utils.model.Student;

public class TestJson {
	
	@Test
	public void mains() {
//		jmap -dump:format=b,file=heap.bin 3676 
//	     1. Histogram 提供每个类的对象统计，包括对象的个数以及大小。
//			Objects:类的对象的数量。
//			Shallow Heap：就是对象本身占用内存的大小，不包含对其他对象的引用，也就是对象头加成员变量（不是成员变量的值）的总和。
//			Retained Heap：当前对象大小+当前对象可直接或间接引用到的对象的大小总和。(间接引用的含义：A->B->C, C就是间接引用) 
//					换句话说，Retained Heap是该对象被GC之后所能回收到内存的总和
		
//	     2. Dominator Tree(支配树)可以列出那个线程，以及线程下面的那些对象占用的空间。提供程序中最占内存的对象
//			Shallow Heap
//			Retained Heap
//			Percentage	百分比
		
//	     3. Top consumers(消耗内存排名)通过图形列出最大的object。
//			Biggest Objects
//			Biggest Top-Level Dominator Classes
//			Biggest Top-Level Dominator Class Loaders
//			Biggest Top-Level Dominator Packages
		
//	     4. Leak Suspects(疑似泄露点)通过MA自动分析泄漏的原因。
		
//		Path to GC Roots ： 被JVM持有的对象，如当前运行的线程对象，被systemclass loader加载的对象被称为GC Roots， 从一个对象到GC Roots的引用链被称为Path to GC Roots
//		list objects -- with outgoing references : 查看这个对象持有的外部对象引用。
//		list objects -- with incoming references : 查看这个对象被哪些外部对象引用。
//		show objects by class  --  with outgoing references ：查看这个对象类型持有的外部对象引用
//		show objects by class  --  with incoming references ：查看这个对象类型被哪些外部对象引用  
		
		
		
		Student stu = new Student();
		stu.setName("name");
		
		System.out.println(JSONPath.eval(stu, "$.name"));
		System.out.println(JSONPath.contains(stu, "$.name"));
		System.out.println(JSONPath.containsValue(stu, "$.name", stu.getName()));
		System.out.println(JSONPath.size(stu, "$"));
		
		List<Student> list = new ArrayList<Student>();
		for(int i=0; i<10; i++){
			Student stu1 = new Student();
			stu1.setName("name" + i);
			list.add(stu1);
		}
		List<String> eval = (List<String>)JSONPath.eval(list, "$.name");
		System.out.println(eval);
		System.out.println((List<Student>)JSONPath.eval(list, "[2,3]"));
		System.out.println((List<Student>)JSONPath.eval(list, "[0:3]"));
		System.out.println((List<Student>)JSONPath.eval(list, "[name in ('name1')]"));
		System.out.println((List<Student>)JSONPath.eval(list, "[name = 'name1']"));
		
    }
	
}
