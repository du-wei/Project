/** @Title: TestUpload.java
 * @Package com.webapp.action
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-31 下午5:39:36
 * @version V1.0 */
package com.webapp.junit.junit;

import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

/** @ClassName: TestUpload.java
 * @Package com.webapp.action
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-31 下午5:39:36
 * @version V1.0 */
public class StrutsSpringTest extends StrutsSpringTestCase {

	@Override
	protected String[] getContextLocations() {
		return new String[]
		{ "applicationContext.xml" };
	}

	@Test
	public void testJson() throws Exception {
		ActionMapping map = getActionMapping("/jsonAction!loadJson");
		System.out.println(map.getMethod());
		System.out.println(map.getName());
		System.out.println(map.getNamespace());
		System.out.println(map.getParams());
		System.out.println(map.getResult());

		ActionProxy proxy = getActionProxy("/jsonAction!loadJson");
		System.out.println(proxy.getActionName());
		System.out.println(proxy.getExecuteResult());
		System.out.println(proxy.execute());

		String result = executeAction("/jsonAction!loadJson");
		System.out.println(result);
	}

	@Test
	public void testUpload() throws Exception {

		// ApplicationContext ctx = new
		// ClassPathXmlApplicationContext("applicationContext.xml");
		// UploadAction upload = (UploadAction) ctx.getBean("uploadAction");
		// String result = upload.execute();
		// System.out.println(result);

		request.setContentType("multipart/form-data");
		request.addHeader("enctype", "multipart/form-data");
		request.addParameter("upload", "E:/TDDOWNLOAD/xx.jpg");

		//
		// ActionMapping mapping = getActionMapping("/jsonAction");
		// ActionProxy proxy = getActionProxy("/upload!uploadWithJavaIO");
		// assertNotNull(proxy.getActionName());
		// String result = proxy.execute();
		// System.out.println(result);

	}

}
