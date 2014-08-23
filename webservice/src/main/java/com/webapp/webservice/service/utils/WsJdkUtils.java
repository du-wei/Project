/**   
 * @Title: JDKUtils.java 
 * @Package com.webapp.cxf 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-3-19 下午5:31:12 
 * @version V1.0   
 */
package com.webapp.webservice.service.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

/**
 * @ClassName: JDKUtils.java
 * @Package com.webapp.cxf
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-19 下午5:31:12
 * @version V1.0
 */
public class WsJdkUtils {

	/*
	 * JAXWS2.0 提供的工具封装 wsgen 主要用于Server端通过Java类编译成Webservice及相关的wsdl文件
	 * -wsdl参数代表生成webservice -s 参数代表生成的.java文件置于何处 -d
	 * 参数代表生成的编译class文件置于何处（这个可以忽略，我们利用eclipse编译） -r 参数代表生成的.wsdl文件与.xsd文件生成在何处
	 * -cp参数代表classpath wsimport 主要用于Client端（调用端）通过wsdl编译出调用Server端的Java文件 -keep
	 * 保存Java文件 -d:生成客户端执行类的class文件的存放目录 　　 * -s:生成客户端执行类的源文件的存放目录 　　 *
	 * -p:定义生成类的包名
	 */
	public static Logger logger = Logger.getLogger(WsJdkUtils.class);
	public static final String dir_web = "src";
	public static final String dir_maven = "src/main/java";

	public static void main(String[] args) {
		WSDL2Java("http://localhost:8080/webservice/service?wsdl");
	}

	public static void WSDL2Java(String source) {
		WSDL2Java(source, null);
	}

	public static void WSDL2Java(String source, String dir) {
		WSDL2Java(source, dir, null);
	}

	/**
	 * @Title: WSDL2Java
	 * @Description: TODO 方法描述
	 * @param dir
	 *            目录 pack 包名
	 * @return void 返回类型描述
	 * @throws
	 */
	public static void WSDL2Java(String source, String dir, String pack) {

		if (source.startsWith("http:/")) {
		} else if (Paths.get(source).isAbsolute()) {
			source = "-wsdllocation " + source;
		} else if (Paths.get(WsJdkUtils.class.getResource(source).getPath())
				.isAbsolute()) {
			source = "-wsdllocation "
					+ encode(WsJdkUtils.class.getResource(source));
		}

		String commond = "wsimport -verbose";
		if (pack != null) {
			commond += (" -p " + pack);
		}
		if (dir == null || dir.equals("")) {
			dir = " -s " + WsJdkUtils.dir_maven;
		}
		commond += dir;

		if (!Paths.get(dir).isAbsolute()) {
			commond += (" -d " + encode(WsJdkUtils.class.getResource("/")));
		}
		commond += (" " + source);

		logger.info(commond);
		try {
			Process process = Runtime.getRuntime().exec(commond);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					process.getInputStream(), "gbk"));

			String info = "";
			while ((info = reader.readLine()) != null) {
				logger.info(info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String encode(URL url) {
		try {
			return Paths.get(url.toURI()).toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
