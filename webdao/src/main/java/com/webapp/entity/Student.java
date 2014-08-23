/**   
 * @Title: Student.java 
 * @Package com.webapp.entity 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-2-18 下午2:36:53 
 * @version V1.0   
 */
package com.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName: Student.java
 * @Package com.webapp.entity
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-18 下午2:36:53
 * @version V1.0
 */
@Entity
public class Student {

	private int id;
	private String name;
	private int age;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
