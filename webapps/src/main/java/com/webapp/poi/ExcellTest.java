package com.webapp.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

public class ExcellTest {

	private Logger logger = Logger.getLogger(ExcellTest.class);
	private static Workbook wb;

	@Test
	public void testPoi() throws Exception {
		// InputStream inputStream = new FileInputStream("F:/test/test.xls");
		//
		// POIFSFileSystem fileSystem = new POIFSFileSystem(inputStream);
		//
		// Workbook work = WorkbookFactory.create(fileSystem);
		//
		// Sheet sheet = work.getSheetAt(0);
		//
		// logger.error("name - > " + sheet.getSheetName());
		// logger.error("rows - > " + (sheet.getLastRowNum() + 1));
		// each(sheet);

		List<User> list = new ArrayList<>();

		for (int i = 0; i < 1000; i++) {
			User user = new User();
			user.setAge(10);
			user.setId(i);
			list.add(user);
		}

		ExcelUtils utils = ExcelUtils.getInstance();
		utils.exportObj2Excel("F:/ok.xls", list, User.class, false);
	}

	public static String getCellValue(Cell cell) {
		String result = "";
		int type = cell.getCellType();
		switch (type) {
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_ERROR:
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			result = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			result = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			result = String.valueOf(cell.getStringCellValue());
			break;
		default:
			break;
		}
		return result;
	}

	public static void each(Sheet sheet) {
		for (Row row : sheet) {
			for (Cell cell : row) {
				System.out.print(cell.getCellType() + " - ");
				System.out.print(getCellValue(cell));
			}
			System.out.println();
		}
	}

}
