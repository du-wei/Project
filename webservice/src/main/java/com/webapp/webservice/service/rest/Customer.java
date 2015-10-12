/**   
 * @Title: Customer.java 
 * @Package com.webapp.rest 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-13 下午1:39:27 
 * @version V1.0   
 */
package com.webapp.webservice.service.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName: Customer.java
 * @Package com.webapp.rest
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午1:39:27
 * @version V1.0
 */

@XmlRootElement
public class Customer {

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
