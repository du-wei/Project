package com.webapp.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.struts2.ServletActionContext;

public class UploadUtil {

	public static String uploadImage(String fileName, String folder) {
		String dir = "";// ServletActionContext.getServletContext().getRealPath("images");
		if (fileName == null || fileName == "")
			return "failure";

		File path = new File(dir + "/" + folder);
		if (!path.exists())
			path.mkdirs();

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
		String ext = "/" + folder + "-"
				+ f.format((Calendar.getInstance()).getTime()) + ".png";
		String filePath = path + ext;

		File file = new File(fileName);
		try {
			BufferedInputStream bufferInput = new BufferedInputStream(
					new FileInputStream(file));
			BufferedOutputStream bufferOut = new BufferedOutputStream(
					new FileOutputStream(filePath));
			int temp = 0;
			while ((temp = bufferInput.read()) != -1) {
				bufferOut.write(temp);
			}
			bufferInput.close();
			bufferOut.close();
		} catch (FileNotFoundException e) {
			System.out.println("hello  FileNotFoundException ");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("hello IOException");
			e.printStackTrace();
		}

		String picPath = "images/" + folder + ext;
		return picPath;
	}

	/** Prevent instantiation */
	private UploadUtil() {
	}
}
