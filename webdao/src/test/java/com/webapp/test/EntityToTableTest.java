/**   
 * @Title: EntityToTableTest.java 
 * @Package com.webapp.test 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-2-18 下午2:17:27 
 * @version V1.0   
 */
package com.webapp.test;

import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.stat.JdbcStatManager;
import com.webapp.entity.Student;

/**
 * @ClassName: EntityToTableTest.java
 * @Package com.webapp.test
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-18 下午2:17:27
 * @version V1.0
 */
public class EntityToTableTest {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void beforeClass() {
		getSessionFactory2();
	}

	private static void getSessionFactory2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"classpath*:applicationContext.xml");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
	}

	public static void getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}

	@Test
	public void testSchemaExport() {
		new SchemaExport(new Configuration().configure()).create(true, false);
		System.out.println("xxx");
	}

	// @Test
	public void testStart() throws Exception {
		ManagementFactory.getPlatformMBeanServer().registerMBean(
				JdbcStatManager.getInstance(),
				new ObjectName("com.alibaba.druid:type=JdbcStatManager"));
		TabularData data = JdbcStatManager.getInstance().getSqlList();
		System.out.println(getRowNames(data));
		System.out.println(getIndexNames(data));
		System.out.println(getCount(data));
		getset(data);
		Thread.sleep(200);
		Student student = new Student();
		student.setAge(12);
		student.setName("chenglong");
		sessionFactory.openSession().save(student);

		data = JdbcStatManager.getInstance().getSqlList();
		System.out.println(getRowNames(data));
		System.out.println(getIndexNames(data));
		System.out.println(getCount(data));
		getset(data);
	}

	// @Test
	public void testAuto() throws Exception {

		String[] itemNames = { "name", "subject", "score" };
		String[] itemDesc = { "name of student", "subject", "score" };
		OpenType[] itemTypes = { SimpleType.STRING, SimpleType.STRING,
				SimpleType.STRING };
		CompositeType comType = new CompositeType("score", " score desc",
				itemNames, itemDesc, itemTypes);

		Object[] itemValues = { "chenglong", "shuxue", "12" };
		CompositeData result = new CompositeDataSupport(comType, itemNames,
				itemValues);
		Object[] itemValues1 = { "chenglong2", "shuxue2", "122" };
		CompositeData result1 = new CompositeDataSupport(comType, itemNames,
				itemValues1);

		String[] indexNames = { "name" };
		TabularType tabType = new TabularType("scores", "all scores", comType,
				indexNames);

		TabularData tabData = new TabularDataSupport(tabType);
		tabData.put(result);
		tabData.put(result1);

		System.out.println("----value-----");
		Set set = tabData.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Object element = (Object) it.next();
			System.out.println("\t" + element);
		}

		Object[] item = { "chenglong" };
		System.out.println(tabData.get(item).get("name"));

		System.out.println(tabData.getTabularType().getIndexNames());

		System.out.println(tabData.getTabularType().getRowType().keySet());

		System.out.println(tabData.getTabularType().getRowType()
				.getType("name").getTypeName());

	}

	public Set<String> getRowNames(TabularData data) {
		return data.getTabularType().getRowType().keySet();
	}

	public List<String> getIndexNames(TabularData data) {
		return data.getTabularType().getIndexNames();
	}

	public int getCount(TabularData data) {
		return data.size();
	}

	public void getset(TabularData data) {
		List<String> keys = data.getTabularType().getIndexNames();
		keys.indexOf("");
		Set<List<Object>> set = (Set<List<Object>>) data.keySet();
		Iterator<List<Object>> it = set.iterator();
		while (it.hasNext()) {
			List<Object> element = it.next();

			System.out.println("DbType			-->"
					+ element.get(keys.indexOf("DbType")));
			System.out.println("SQL			-->" + element.get(keys.indexOf("SQL")));
			System.out.println("URL			-->" + element.get(keys.indexOf("URL")));
			System.out.println("数据源			-->"
					+ element.get(keys.indexOf("DataSource")));
			System.out.println("影响行数			-->"
					+ element.get(keys.indexOf("EffectedRowCount")));
			System.out.println("读取行数			-->"
					+ element.get(keys.indexOf("FetchRowCount")));
			System.out.println("执行次数			-->"
					+ element.get(keys.indexOf("ExecuteCount")));
			System.out.println("最大的batch		-->"
					+ element.get(keys.indexOf("BatchSizeMax")));
			System.out.println("执行错误数		-->"
					+ element.get(keys.indexOf("ErrorCount")));
			System.out.println("最大并发执行此SQL的数量	-->"
					+ element.get(keys.indexOf("ConcurrentMax")));
			System.out.println("最后一次执行的时间		-->"
					+ element.get(keys.indexOf("LastTime")));
			System.out.println("最慢消耗时间		-->"
					+ element.get(keys.indexOf("MaxTimespan")));
			System.out.println("正在执行的次数		-->"
					+ element.get(keys.indexOf("RunningCount")));
			System.out.println("最慢发生的时间		-->"
					+ element.get(keys.indexOf("MaxTimespanOccurTime")));
			System.out.println("所有的batch数量		-->"
					+ element.get(keys.indexOf("BatchSizeTotal")));
			System.out.println("TotalTime		-->"
					+ element.get(keys.indexOf("TotalTime")));
		}
	}
}
