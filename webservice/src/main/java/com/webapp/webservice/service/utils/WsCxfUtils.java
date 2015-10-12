/**
 * @Title: CxfJavaToWsdl.java
 * @Package com.webapp.cxf
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午6:00:44
 * @version V1.0
 */
package com.webapp.webservice.service.utils;

import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.cxf.tools.java2ws.JavaToWS;
import org.apache.cxf.tools.wsdlto.WSDLToJava;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: CxfJavaToWsdl.java
 * @Package com.webapp.cxf
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-3-13 下午6:00:44
 * @version V1.0
 */
public class WsCxfUtils {

	public static Logger logger = LogManager.getLogger(WsJdkUtils.class);
	public static final String type_client = "-client";
	public static final String type_server = "-server";
	public static final String type_impl = "-impl";
	public static final String type_all = "-all";
	public static final String dir_web = "src";
	public static final String dir_maven = "src/main/java";

	public static void main(String[] args) {
		// WebServiceUtils.Java2WSDL(CxfService.class);
		WsCxfUtils.WSDL2Java("http://localhost:8080/ws/cxf?wsdl",
				WsCxfUtils.dir_maven, "com.webapp.cxf.client",
				WsCxfUtils.type_client);
	}

	/**
	 * @param className
	 *            interface
	 */
	public static void Java2WSDL(Class<?> className) {
		Java2WSDL(className, "", "");
	}

	public static void Java2WSDL(Class<?> className, String filePath) {
		Java2WSDL(className, filePath, "");
	}

	public static void Java2WSDL(Class<?> className, String filePath,
			String fileName) {
		filePath = (filePath == null || filePath.equals("")) ? "" : filePath;
		fileName = (fileName == null || fileName.equals("")) ? className
				.getSimpleName() : fileName;
		String[] args = new String[] { "-o", fileName + ".wsdl", "-wsdl", "-d",
				"./" + filePath, className.getName() };
		JavaToWS.main(args);
	}

	public static void WSDL2Java(String source) {
		WSDL2Java(source, null, null, WsCxfUtils.type_client);
	}

	public static void WSDL2Java(String source, String dir) {
		WSDL2Java(source, dir, null, WsCxfUtils.type_client);
	}

	public static void WSDL2Java(String source, String dir, String pack,
			String type) {

		if (!(source.contains("http://") || Paths.get(source).isAbsolute())) {
			source = WsCxfUtils.class.getClassLoader().getResource(source)
					.getPath();
		}
		dir = (dir == null || dir.equals("")) ? WsCxfUtils.dir_maven : dir;
		type = (type == null) ? WsCxfUtils.type_client : type;

		String[] args = new String[] { "-d", dir, "-p", pack, type, source };
		if (pack == null || pack.equals("")) {
			args = new String[] { "-d", dir, type, source };
		}

		logger.info(Arrays.toString(args));
		WSDLToJava.main(args);
	}

}
