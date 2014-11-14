package com.webapp.module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Report {
	public static void main(String[] args) {
		xml();
	}

	public static void xml() {
		// try {
		// String root = System.getProperty("user.dir");
		// File file = new File("D:\\report.jasper");
		// JasperReport report = (JasperReport) JRLoader.loadObject(file);
		//
		// JasperPrint print = JasperFillManager.fillReport(report, null,
		// DBConnection.getCon());
		//
		// JRXmlExporter xml = new JRXmlExporter();
		// xml.setParameter(JRExporterParameter.JASPER_PRINT, print);
		// xml.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		// xml.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
		// "D:\\xml.xml");
		// xml.exportReport();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// DBConnection.close();
		// }
	}

	public static void pdf() {
		// try {
		// String root = System.getProperty("user.dir");
		//
		// // JasperCompileManager.compileReportToFile("D:\\report.jrxml", root
		// // + "/WebRoot/images/report.jasper");
		// // File file = new File(root + "/WebRoot/images/report.jasper");
		// File file = new File("D:\\report.jasper");
		//
		// Map map = new HashMap();
		// map.put("name", "name");
		// JasperReport report = (JasperReport) JRLoader.loadObject(file);
		//
		// JasperPrint print = JasperFillManager.fillReport(report, map,
		// DBConnection.getCon());
		//
		// JRPdfExporter pdf = new JRPdfExporter();
		// pdf.setParameter(JRExporterParameter.JASPER_PRINT, print);
		// pdf.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		// pdf.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
		// "D:\\pdf.pdf");
		// pdf.exportReport();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// DBConnection.close();
		// }
	}

	public static void html() {
		// try {
		// String root = System.getProperty("user.dir");
		//
		// JasperCompileManager.compileReportToFile("D:\\report.jrxml", root
		// + "/WebRoot/images/report.jasper");
		//
		// File file = new File(root + "/WebRoot/images/report.jasper");
		//
		// JasperReport report = (JasperReport) JRLoader.loadObject(file);
		//
		// JasperPrint print = JasperFillManager.fillReport(report, null,
		// DBConnection.getCon());
		//
		// JRHtmlExporter html = new JRHtmlExporter();
		// html.setParameter(JRExporterParameter.JASPER_PRINT, print);
		// html.setParameter(JRExporterParameter.CHARACTER_ENCODING, "GBK");
		// html.setParameter(JRExporterParameter.OUTPUT_WRITER,
		// new PrintWriter("D:\\html.html"));
		// html.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
		// Boolean.FALSE);
		// html.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
		// "<br style='page-break-before:always;'>");
		// html.exportReport();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// DBConnection.close();
		// }
	}

	/** Prevent instantiation */
	private Report() {
	}
}

class DBConnection {
	private static Connection con;

	public static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager
			        .getConnection("jdbc:mysql://localhost:3306/company?user=root&password=root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void close() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
