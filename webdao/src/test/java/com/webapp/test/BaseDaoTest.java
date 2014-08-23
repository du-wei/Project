/**   
 * @Title: BaseDaoTest.java 
 * @Package com.webapp.test 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-2-21 下午5:47:56 
 * @version V1.0   
 */
package com.webapp.test;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.SessionFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.webapp.dao.BaseDAO;
import com.webapp.entity.Student;
import com.webapp.util.SpringContextUtil;

/**
 * @ClassName: BaseDaoTest.java
 * @Package com.webapp.test
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-21 下午5:47:56
 * @version V1.0
 */
public class BaseDaoTest {

	private static BaseDAO dao;

	@BeforeClass
	public static void beforeClass() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath*:applicationContext.xml");
		// sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		dao = (BaseDAO) ctx.getBean("baseDao");
	}

	@Test
	public void save() {
		Student stu = getStudent();
		System.out.println(dao.save(stu));
	}

	@Test
	public void saveOrUpdate() {
		Student stu = getStuId();
		dao.saveOrUpdate(stu);
	}

	@Test
	public void update() {
		Student stu = getStuId();
		stu.setAge(100);
		dao.update(stu);
	}

	@Test
	public void delById() {
		dao.delById(Student.class, 6);
	}

	@Test
	public void loadById() {
		Student stu = dao.loadById(Student.class, 7);
		System.out.println(stu.getId() + stu.getName());
	}

	@Test
	public void saveList() {
		List<Student> list = new ArrayList<>();
		list.add(getStudent());
		list.add(getStudent());
		list.add(getStudent());
		dao.save(list);
	}

	@Test
	public void daoTest() {
		// save();
		// saveOrUpdate();
		saveList();
	}

	public Student getStudent() {
		Student stu = new Student();
		stu.setAge(20);
		stu.setName("king");
		return stu;
	}

	public Student getStuId() {
		Student stu = new Student();
		stu.setId(1);
		stu.setAge(20);
		stu.setName("king");
		return stu;
	}
}
