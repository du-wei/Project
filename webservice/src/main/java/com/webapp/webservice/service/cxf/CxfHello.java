package com.webapp.webservice.service.cxf;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@WebService
public interface CxfHello {

	@WebMethod(action = "sayHelloAction", operationName = "sayHello")
	@WebResult(name = "sayHelloResult")
	public String sayHello(@WebParam(name = "str") String str);

	@WebMethod(action = "sayFaultAction", operationName = "sayFault")
	@WebResult(name = "sayFaultResult")
	public String sayFault(@WebParam(name = "str") String str) throws Exception;

	@WebMethod(action = "sayListAction", operationName = "sayList")
	@WebResult(name = "sayListResult")
	public List<String> sayList(@WebParam(name = "str") String str);

	public List<CxfUser> getUsers();

	@XmlJavaTypeAdapter(CxfAdapter.class)
	public Map<String, CxfUser> getMapUser();

}
