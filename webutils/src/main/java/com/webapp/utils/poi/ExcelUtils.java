package com.webapp.utils.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtils {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

	public static void createExcel(String outPath, List<?> objs, Class<?> clz,
			boolean isXssf) {
		FileOutputStream fos = null;
		try {
			Workbook wb = createExcel(objs, clz, isXssf);
			fos = new FileOutputStream(outPath);
			wb.write(fos);
		} catch (Exception e) {
			logger.error("生成Excel失败", e);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void createExcelByTemplate(Map<String, String> datas,
			String template, String outPath, List<?> objs, Class<?> clz,
			boolean isClasspath) {
		ExcelTemplate et = createExcelByTemplate(template, objs, clz,
				isClasspath);
		et.replaceData(datas).writeToFile(outPath);
	}

	private static Workbook createExcel(List<?> objs, Class<?> clz,
			boolean isXssf) throws Exception {
		Workbook wb = isXssf ? new XSSFWorkbook() : new HSSFWorkbook();

		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(0);
		List<ExcelHeader> headers = createTitle(clz, row);
		// 写数据
		Object obj = null;
		for (int i = 0; i < objs.size(); i++) {
			row = sheet.createRow(i + 1);
			obj = objs.get(i);
			for (int j = 0; j < headers.size(); j++) {
				row.createCell(j)
						.setCellValue(
								BeanUtils.getProperty(obj, headers.get(j)
										.getPropName()));
			}
		}
		return wb;
	}

	private static ExcelTemplate createExcelByTemplate(String template,
			List<?> objs, Class<?> clz, boolean isClasspath) {
		ExcelTemplate et = ExcelTemplate.getInstance();
		try {
			et = isClasspath ? et.readTemplateByClasspath(template) : et
					.readTemplateByPath(template);

			List<ExcelHeader> headers = getExcelHeaders(clz);
			Collections.sort(headers);
			// 输出标题
			et.newRow();
			for (ExcelHeader eh : headers) {
				et.setCellVal(eh.getTitle());
			}
			// 输出值
			for (Object obj : objs) {
				et.newRow();
				for (ExcelHeader eh : headers) {
					et.setCellVal(BeanUtils.getProperty(obj, eh.getPropName()));
				}
			}
		} catch (Exception e) {
			logger.error("通过模版创建Excel失败", e);
		}
		return et;
	}

	private static List<ExcelHeader> createTitle(Class<?> clz, Row row) {
		List<ExcelHeader> headers = getExcelHeaders(clz);
		Collections.sort(headers);
		// 写标题
		List<String> titles = new ArrayList<>();
		for (int i = 0; i < headers.size(); i++) {
			titles.add(headers.get(i).getTitle());
		}
		createTitle(titles, row);
		return headers;
	}

	private static void createTitle(List<String> titles, Row row) {
		// 写标题
		for (int i = 0; i < titles.size(); i++) {
			row.createCell(i).setCellValue(titles.get(i));
		}
	}

	public static <T> List<T> readExcelByClasspath(String path, Class<T> clz,
			int readLine, int tailLine) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(ExcelUtils.class
					.getResourceAsStream(path));
			return handlerExcel2Objs(wb, clz, readLine, tailLine);
		} catch (Exception e) {
			logger.error("通过模版创建Excel失败", e);
		}
		return null;
	}

	public static List<String[]> readExcelByClasspath(String path,
			int readLine, int tailLine) {
		Workbook wb = null;
		try {
			wb = WorkbookFactory.create(ExcelUtils.class
					.getResourceAsStream(path));
			return handlerExcel2Objs(wb, readLine, tailLine);
		} catch (Exception e) {
			logger.error("通过模版创建Excel失败", e);
		}
		return null;
	}

	public static <T> List<T> readExcelByPath(String path, Class<T> clz,
			int readLine, int tailLine) {
		Workbook wb = null;
		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(
					new FileInputStream(path));
			wb = WorkbookFactory.create(fileSystem);
			return handlerExcel2Objs(wb, clz, readLine, tailLine);
		} catch (IOException e) {
			logger.error("通过模版创建Excel失败", e);
		}
		return null;
	}

	public static List<String[]> readExcelByPath(String path, int readLine,
			int tailLine) {
		Workbook wb = null;
		try {
			POIFSFileSystem fileSystem = new POIFSFileSystem(
					new FileInputStream(path));
			wb = WorkbookFactory.create(fileSystem);
			return handlerExcel2Objs(wb, readLine, tailLine);
		} catch (IOException e) {
			logger.error("通过模版创建Excel失败", e);
		}
		return null;
	}

	public static <T> List<T> readExcelByClasspath(String path, Class<T> clz) {
		return readExcelByClasspath(path, clz, 0, 0);
	}

	public static <T> List<T> readExcelByPath(String path, Class<T> clz) {
		return readExcelByPath(path, clz, 0, 0);
	}

	private static String getCellValue(Cell c) {
		String o = null;
		switch (c.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			o = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			o = String.valueOf(c.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			o = String.valueOf(c.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			o = String.valueOf(c.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			o = c.getStringCellValue();
			break;
		default:
			o = null;
			break;
		}
		return o;
	}

	private static <T> List<T> handlerExcel2Objs(Workbook wb, Class<T> clz,
			int readLine, int tailLine) {
		Sheet sheet = wb.getSheetAt(0);
		List<T> ts = null;
		try {
			Row row = sheet.getRow(readLine);
			ts = new ArrayList<T>();
			Map<Integer, String> maps = getHeaderMap(row, clz);
			if (maps == null || maps.size() <= 0)
				throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");
			for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {
				row = sheet.getRow(i);
				T t = clz.newInstance();
				for (Cell c : row) {
					int ci = c.getColumnIndex();
					String mn = maps.get(ci).substring(3);
					mn = mn.substring(0, 1).toLowerCase() + mn.substring(1);
					BeanUtils.copyProperty(t, mn, getCellValue(c));
				}
				ts.add(t);
			}
		} catch (Exception e) {
			logger.error("通过模版创建Excel失败", e);
		}
		return ts;
	}

	private static List<String[]> handlerExcel2Objs(Workbook wb, int readLine,
			int tailLine) {
		Sheet sheet = wb.getSheetAt(0);
		List<String[]> result = new ArrayList<>();
		try {
			Row row = sheet.getRow(readLine);

			for (int i = readLine + 1; i <= sheet.getLastRowNum() - tailLine; i++) {
				row = sheet.getRow(i);
				String[] rowData = new String[row.getLastCellNum()];
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell c = row.getCell(j);
					rowData[j] = getCellValue(c);
				}
				result.add(rowData);
			}
		} catch (Exception e) {
			logger.error("通过模版创建Excel失败", e);
		}
		return result;
	}

	private static List<ExcelHeader> getExcelHeaders(Class<?> clz) {
		List<ExcelHeader> headers = new ArrayList<>();
		Method[] ms = clz.getDeclaredMethods();
		for (Method m : ms) {
			String name = m.getName();
			if (name.startsWith("get")
					&& m.isAnnotationPresent(ExcelResources.class)) {
				ExcelResources er = m.getAnnotation(ExcelResources.class);
				headers.add(new ExcelHeader(er.title(), er.order(), name));
			}
		}
		return headers;
	}

	private static Map<Integer, String> getHeaderMap(Row titleRow, Class<?> clz) {
		List<ExcelHeader> headers = getExcelHeaders(clz);
		Map<Integer, String> maps = new HashMap<>();
		for (Cell c : titleRow) {
			String title = c.getStringCellValue();
			for (ExcelHeader eh : headers) {
				if (eh.getTitle().equals(title.trim())) {
					maps.put(c.getColumnIndex(),
							eh.getMethodName().replace("get", "set"));
					break;
				}
			}
		}
		return maps;
	}

}
