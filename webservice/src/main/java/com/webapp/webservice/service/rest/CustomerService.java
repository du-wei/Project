/**   
 * @Title: CustomerService.java 
 * @Package com.webapp.rest 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-13 下午1:40:18 
 * @version V1.0   
 */
package com.webapp.webservice.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @ClassName: CustomerService.java
 * @Package com.webapp.rest
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午1:40:18
 * @version V1.0
 */
@Path(value = "/customer")
@Produces("*/*")
public interface CustomerService {

	@GET
	@Path("/{id}/info")
	public Customer getById(String id);
}
