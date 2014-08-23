package com.webapp.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String download() {
		return SUCCESS;
	}

	public InputStream getInputStream() throws Exception {
		String dir = ServletActionContext.getServletContext().getRealPath(
				"/images");
		File file = new File(dir, "/image.png");
		System.out.println(file.getAbsolutePath());
		if (file.exists()) {
			System.out.println("========================");
			return new FileInputStream(file);
		}
		return null;
	}

	public String getFileName() {
		return "image.png";
	}
}
