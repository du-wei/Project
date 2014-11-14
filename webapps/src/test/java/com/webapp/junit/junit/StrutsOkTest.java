/** @Title: StrutsTest.java
 * @Package com.webapp.junit
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2012-11-13 下午12:56:34
 * @version V1.0 */
package com.webapp.junit.junit;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionProxy;

/** @ClassName: StrutsTest.java
 * @Package com.webapp.junit
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2012-11-13 下午12:56:34
 * @version V1.0 */
public class StrutsOkTest extends StrutsTestCase {
	@BeforeClass
	public static void beforeClass() {

	}

	@AfterClass
	public static void afterClass() {

	}

	// @Test
	public void testType() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
		        "applicationContext.xml");
		ctx.getBean("typeAction");
		// assertThat(str, is("typeJson"));
		fail("Missing assertions/failures");
	}

	// 测试Action配置文件是否正确
	public void testGetActionMapping() {
		ActionMapping mapping = getActionMapping("/test");
		assertNotNull("assertNotNull(mapping) failed", mapping);
		assertEquals("assertEquals(\"/\",mapping.getNamespace()) failed", "/",
		        mapping.getNamespace());
		assertEquals("assertEquals(\"test\",mapping.getName()) failed", "test",
		        mapping.getName());
	}

	// 测试Action代理
	public void testGetActionProxy() throws Exception {
		request.setParameter("name", "Chase");

		ActionProxy proxy = getActionProxy("/test/strutsTest");
		StrutsOkTest action = (StrutsOkTest) proxy.getAction();
		String result = proxy.execute();
		System.out.println(result);
		fail("Missing assertions/failures");
	}

	// 测试重写的execute()方法
	public void testExecuteAction() throws ServletException,
	        UnsupportedEncodingException {
		String output = executeAction("/test/strutsTest");
		System.out.println(output);
		fail("Missing assertions/failures");
	}

	public void testGetValueFromStack() throws ServletException,
	        UnsupportedEncodingException {
		request.setParameter("name", "Chase");
		executeAction("/test/strutsTest");
		String name = (String) findValueAfterExecute("name");
		System.out.println(name);
		fail("Missing assertions/failures");
	}

}
