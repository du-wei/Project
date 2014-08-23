/**   
 * @Title: CustomerServiceImpl.java 
 * @Package com.webapp.rest 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-13 下午1:43:46 
 * @version V1.0   
 */
package com.webapp.webservice.service.rest;

/**
 * @ClassName: CustomerServiceImpl.java
 * @Package com.webapp.rest
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午1:43:46
 * @version V1.0
 */
public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer getById(String id) {
		System.out.println("hello " + id);
		return null;
	}

}
