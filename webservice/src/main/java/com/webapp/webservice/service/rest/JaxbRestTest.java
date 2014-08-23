/**   
 * @Title: JaxbRestTest.java 
 * @Package com.webapp.rest 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-13 下午1:38:56 
 * @version V1.0   
 */
package com.webapp.webservice.service.rest;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @ClassName: JaxbRestTest.java
 * @Package com.webapp.rest
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午1:38:56
 * @version V1.0
 */
public class JaxbRestTest {

	public static void main(String[] args) throws Exception {
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		factoryBean.setResourceClasses(CustomerServiceImpl.class);
		factoryBean.setAddress("http://localhost:8080/ws/jaxrs");
		factoryBean.create();

		go("http://localhost:8080/ws/jaxrs/customer/1/info");

	}

	/**
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @Title: go
	 * @Description: TODO 方法描述
	 * @param @param string 参数描述
	 * @return void 返回类型描述
	 * @throws
	 */
	private static void go(String url) throws Exception {
		HttpGet get = new HttpGet(url);

		HttpClient client = new DefaultHttpClient();
		HttpResponse resp = client.execute(get);
		if (resp.getStatusLine().getStatusCode() == 200) {
			System.out.println(resp.getEntity().getContent());
		}

	}

}
