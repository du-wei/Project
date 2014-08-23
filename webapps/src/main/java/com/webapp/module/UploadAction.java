package com.webapp.module;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("uploadAction")
@Scope("prototype")
public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private File upload;
	private String uploadFileName;
	private String javaio;

	public UploadAction() {
	}

	public String uploadWithJavaIO() throws Exception {

		String dir = ServletActionContext.getServletContext().getRealPath(
				"images");
		File file = new File(javaio);

		BufferedInputStream inp = new BufferedInputStream(new FileInputStream(
				file));
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(dir + "/javaio.png"));
		int temp = 0;
		while ((temp = inp.read()) != -1) {
			out.write(temp);
		}
		inp.close();
		out.close();

		return SUCCESS;

	}

	public String uploadWithStruts() throws Exception {

		System.out.println("file :" + upload.toString());
		System.out.println("filename:" + uploadFileName);

		String dir = ServletActionContext.getServletContext().getRealPath(
				"images");
		File target = new File(dir + uploadFileName);
		System.out.println(target);
		FileUtils.copyFile(upload, target);

		return SUCCESS;

	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getJavaio() {
		return javaio;
	}

	public void setJavaio(String javaio) {
		this.javaio = javaio;
	}
}
