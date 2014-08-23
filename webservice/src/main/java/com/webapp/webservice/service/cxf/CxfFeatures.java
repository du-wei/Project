/**   
 * @Title: CxfFeatures.java 
 * @Package com.webapp.cxf 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-13 下午2:24:47 
 * @version V1.0   
 */
package com.webapp.webservice.service.cxf;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.InterceptorProvider;

/**
 * @ClassName: CxfFeatures.java
 * @Package com.webapp.cxf
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午2:24:47
 * @version V1.0
 */
public class CxfFeatures extends AbstractFeature {

	@Override
	protected void initializeProvider(InterceptorProvider provider, Bus bus) {
		super.initializeProvider(provider, bus);
		System.out.println(" features ");
	}

}
