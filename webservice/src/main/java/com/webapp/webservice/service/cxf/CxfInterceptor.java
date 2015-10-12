/**   
 * @Title: CxfInterceptor.java 
 * @Package com.webapp.cxf 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-13 下午2:20:45 
 * @version V1.0   
 */
package com.webapp.webservice.service.cxf;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;

/**
 * @ClassName: CxfInterceptor.java
 * @Package com.webapp.cxf
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午2:20:45
 * @version V1.0
 */
public class CxfInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param phase
	 */
	public CxfInterceptor(String phase) {
		super(phase);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println(" interceptor ");
	}

}
