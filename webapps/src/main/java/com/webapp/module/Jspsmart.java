package com.webapp.module;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Jspsmart extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// long maxSize = 1024 * 1024 * 20;
		// SmartUpload upload = new SmartUpload();
		// upload.initialize(this.getServletConfig(), req, resp);
		// upload.setTotalMaxFileSize(maxSize);
		// try {
		// upload.setDeniedFilesList("exe,dos,bat");
		// Files files = upload.getFiles();
		// System.out.println(files.getCount());
		// for (int i = 0; i < files.getCount(); i++) {
		// File file = files.getFile(i);
		// if (!file.isMissing()) {
		// System.out.println(ServletActionContext.getServletContext().getRealPath("images")+"/jspsmart.png");
		// file.saveAs(ServletActionContext.getServletContext().getRealPath("images")+"/jspsmart.png");
		// }
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } catch (SmartUploadException e) {
		// e.printStackTrace();
		// }
		// System.out.println("jspsmart dopost===============");
		// resp.sendRedirect("/com/jspsmart/upload.jsp");
	}

}
