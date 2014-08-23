package com.webapp.webservice.service.jaxws;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @ClassName: HelloService.java
 * @Package com.webapp.webservice.service.jaxws
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月31日 下午11:21:56
 * @version V1.0
 * 
 *          默认为发布的类名 HelloServiceImpl 属性 wsdl节点 默认名字 name -> portType ->
 *          HelloServiceImpl serviceName -> service -> HelloServiceImplService
 *          portName -> port -> HelloServiceImplPort targetNamespace ->
 *          targetNamespace -> 包名倒置 endpointInterface -> 发布的接口
 * 
 */
@WebService(name = "HelloService", serviceName = "HelloService")
@HandlerChain(file = "handler-chain.xml")
public interface HelloService {

	/*
	 * operationName-> operation action -> soapAction
	 */
	@WebMethod(action = "sayHelloAction", operationName = "sayHello")
	@WebResult(name = "sayHelloResult")
	public String sayHello(@WebParam(name = "str") String str);

	@WebMethod(action = "sayFaultAction", operationName = "sayFault")
	@WebResult(name = "sayFaultResult")
	public String sayFault(@WebParam(name = "str") String str) throws Exception;

	@WebMethod(action = "sayListAction", operationName = "sayList")
	@WebResult(name = "sayListResult")
	public List<String> sayList(@WebParam(name = "str") String str);

}
