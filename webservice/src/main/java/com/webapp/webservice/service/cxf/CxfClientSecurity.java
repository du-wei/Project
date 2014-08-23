/**   
 * @Title: CxfClientSecurity.java 
 * @Package com.webapp.cxf 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-13 下午2:53:36 
 * @version V1.0   
 */
package com.webapp.webservice.service.cxf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * @ClassName: CxfClientSecurity.java
 * @Package com.webapp.cxf
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午2:53:36
 * @version V1.0
 */
public class CxfClientSecurity implements CallbackHandler {
	private Map<String, String> users = new HashMap<String, String>();

	public CxfClientSecurity() {
		users.put("admin", "123456");
		users.put("test", "123");
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			String pass = users.get(pc.getIdentifier());
			if (pass != null) {
				pc.setPassword(pass);
			}
		}
	}

}
