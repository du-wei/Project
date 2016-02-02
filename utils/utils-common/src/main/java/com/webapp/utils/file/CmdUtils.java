package com.webapp.utils.file;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public final class CmdUtils {

	public static String exec(String cmd, String encoding) {

		String result = "";
		try {
			Process exec = Runtime.getRuntime().exec(cmd);
			result = IOUtils.toString(exec.getInputStream(), encoding);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	};
	public static String javap(String file, String encoding) {
		return exec("javap -c " + file, encoding);
	};


}
