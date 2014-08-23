/**   
 * @Title: HttpClient.java 
 * @Package com.webapp.apache 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-6 下午3:45:30 
 * @version V1.0   
 */
package com.webapp.apache;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @ClassName: HttpClient.java
 * @Package com.webapp.apache
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-6 下午3:45:30
 * @version V1.0
 */
public class HttpClient {

	public static void main(String[] args) throws Exception {
		KeyGenerator key = KeyGenerator.getInstance("des");

		SecretKey secretKey = key.generateKey();

		System.out.println(secretKey.getAlgorithm());

	}

}
