package com.webapp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LineCounts {
	private static int whiteLines = 0;
	private static int commentLines = 0;
	private static int tatolLines = 0;
	private static boolean bComment = false;

	public static int computeLines(File file) {
		whiteLines = commentLines = tatolLines = 0;
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(file));
			String lineStr = "";
			while ((lineStr = bf.readLine()) != null) {
				if (lineStr.equals(""))
					continue;
				tatolLines++;
				whiteLines(lineStr);
				commentLines(lineStr);
			}
		} catch (Exception e) {
			System.out.println("输入输出异常　computeLines");
			e.printStackTrace();
		} finally {
			if (bf != null) {
				try {
					bf.close();
					bf = null;
				} catch (Exception e) {
					System.out.println("关闭BufferReader时出错");
				}
			}
		}
		return (tatolLines - commentLines - whiteLines);
	}

	// public static void ComputeDirectoryAndFiles(StringBuffer pathName, int
	// level) {
	// File directory = new File(pathName.toString());
	// File[] files = directory.listFiles();
	// String prefix = "";
	// for (int i = 0; i < files.length; i++) {
	// prefix += "** ";
	// }
	// // 当且仅当此抽象路径名表示的文件存在且是一个目录时，返回true；否则返回 false
	// if (directory.isDirectory()) {
	// for (int i = 0; i < files.length; i++) {
	// if (files[i].isFile()
	// && files[i].getName().matches(
	// "^[a-zA-Z[^0-9]]\\w*.java$")) {
	// computeLines(files[i]);
	// }
	// if (files[i].isDirectory()) {
	//
	// pathName.append("/" + files[i].getName());
	// level++;
	// ComputeDirectoryAndFiles(pathName, level);
	// int start = pathName.toString().length()
	// - files[i].getName().length() - 1;
	// int end = pathName.toString().length();
	// pathName.delete(start, end);
	// level--;
	// }
	// }
	// }
	// }

	// 判断是否为空行
	public static void whiteLines(String lineStr) {
		if (lineStr.matches("^[\\s&&[^\\n]]*$")) {
			whiteLines++;
		}
	}

	// 判断是否是一个注释行
	public static void commentLines(String lineStr) {
		// 这里是单行注释的如 /*.. */或/**. */
		if (lineStr.matches("\\s*/\\*{1,}.*(\\*/).*")) {
			commentLines++;
		}
		// 这里是多行注释的
		// 这里的是当开始为/**或/*但是没有 */ 关闭时
		else if (lineStr.matches("\\s*/\\*{1,}.*[^\\*/].*")) {
			commentLines++;
			bComment = true;
		} else if (true == bComment) {
			commentLines++;
			if (lineStr.matches("\\s*[\\*/]+\\s*")) {
				bComment = false;
			}
		} else if (lineStr.matches("^[\\s]*//.*")) {
			commentLines++;
		}
	}
}
