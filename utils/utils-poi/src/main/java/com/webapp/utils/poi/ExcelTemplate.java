package com.webapp.utils.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelTemplate {

	private static final Logger logger = LoggerFactory.getLogger(ExcelTemplate.class);
	private final static String DATA_LINE = "data";
	private final static String DEFAULT_STYLE = "defaultStyle";
	private final static String STYLES = "styles";
	private final static String AUTO_ID = "autoId";

	private static ExcelTemplate et;
	private Workbook wb;
	private Sheet sheet;

	private int initRowIndex;
	private int initColIndex;
	private int curRowIndex;
	private int curColIndex;
	private Row curRow;
	private int lastRowIndex;
	private int serColIndex;
	private float lineHeight;
	private CellStyle defaultStyle;
	private Map<Integer, CellStyle> styles;
	private Map<String, String> lable;

	public static ExcelTemplate getInstance() {
		return et = (et == null) ? new ExcelTemplate() : et;
	}

	public ExcelTemplate readTemplateByClasspath(String name) {
		return readTemplateByClasspath(name, 0);
	}

	public ExcelTemplate readTemplateByClasspath(String name, int sheetPage) {
		return readTemplate(ExcelTemplate.class.getResourceAsStream(name),
				sheetPage);
	}

	public ExcelTemplate readTemplateByPath(String path) {
		return readTemplateByPath(path, 0);
	}

	public ExcelTemplate readTemplateByPath(String path, int sheetPage) {
		try {
			return readTemplate(Files.newInputStream(Paths.get(path)),
					sheetPage);
		} catch (IOException e) {
			throw new RuntimeException(path + " 该模版不存在或者模版格式有错误，请检查");
		}
	}

	private ExcelTemplate readTemplate(InputStream is, int sheetPage) {
		try {
			wb = WorkbookFactory.create(is);
			initTemplate(sheetPage);
		} catch (InvalidFormatException | IOException e) {
			throw new RuntimeException(" 该模版不存在或者模版格式有错误，请检查");
		}
		return this;
	}

	public void initTemplate(int sheetPage) {
		serColIndex = -1;
		styles = new HashMap<>();
		lable = new HashMap<>();
		// String config = "/excel_config.properties";
		//
		// if(ConfigUtils.hasFileInClassPath(config)){
		// Properties p = ConfigUtils.read(config);
		// lable.put(DATA_LINE, p.getProperty(DATA_LINE, "#"+DATA_LINE));
		// lable.put(DEFAULT_STYLE, p.getProperty(DEFAULT_STYLE,
		// "#"+DEFAULT_STYLE));
		// lable.put(STYLES, p.getProperty(STYLES, "#"+STYLES));
		// lable.put(AUTO_ID, p.getProperty(AUTO_ID, "#"+AUTO_ID));
		// }else {
		lable.put(DATA_LINE, "#" + DATA_LINE);
		lable.put(DEFAULT_STYLE, "#" + DEFAULT_STYLE);
		lable.put(STYLES, "#" + STYLES);
		lable.put(AUTO_ID, "#" + AUTO_ID);
		// }

		sheet = wb.getSheetAt(sheetPage);
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() != Cell.CELL_TYPE_STRING)
					continue;
				String val = cell.getStringCellValue().trim();
				initStyles(cell);
				if (val.contains(lable.get(AUTO_ID))) {
					serColIndex = cell.getColumnIndex();
				}
				if (val.contains(lable.get(DATA_LINE))) {
					initRowIndex = row.getRowNum();
					initColIndex = cell.getColumnIndex();
					curRowIndex = initRowIndex;
					curColIndex = initColIndex;
					lineHeight = row.getHeightInPoints();
				}
			}
		}
		lastRowIndex = sheet.getLastRowNum();
	}

	private void initStyles(Cell cell) {
		String val = cell.getStringCellValue().trim();
		if (val.equals(lable.get(DEFAULT_STYLE))) {
			defaultStyle = cell.getCellStyle();
		}
		if (val.contains(lable.get(STYLES))) {
			styles.put(cell.getColumnIndex(), cell.getCellStyle());
		}
	}

	private void applyStyle(Cell cell) {
		cell.setCellStyle(styles.containsKey(curColIndex) ? styles
				.get(curColIndex) : defaultStyle);
	}

	private Cell newCell() {
		Cell cell = curRow.createCell(curColIndex);
		applyStyle(cell);
		curColIndex++;
		return cell;
	}

	public int newRow() {
		if (lastRowIndex > curRowIndex && curRowIndex != initRowIndex) {
			sheet.shiftRows(curRowIndex, lastRowIndex, 1);
		}
		lastRowIndex++;

		curRow = sheet.createRow(curRowIndex);
		curRow.setHeightInPoints(lineHeight);

		curRowIndex++;
		curColIndex = initColIndex;

		return curRowIndex - initRowIndex;
	}

	public void insertSerial() {
		if (serColIndex >= 0) {
			curColIndex = serColIndex;
			Row row = null;
			Cell cell = null;
			for (int i = 0; i < curRowIndex - initRowIndex; i++) {
				row = sheet.getRow(i + initRowIndex);
				cell = row.createCell(serColIndex);
				applyStyle(cell);
				cell.setCellValue(i + 1);
			}
		}
	}

	public ExcelTemplate setCellVal(String value) {
		newCell().setCellValue(value);
		return this;
	}

	public ExcelTemplate setCellVal(double value) {
		newCell().setCellValue(value);
		return this;
	}

	public ExcelTemplate setCellVal(boolean value) {
		newCell().setCellValue(value);
		return this;
	}

	public ExcelTemplate setCellVal(Date value) {
		newCell().setCellValue(value);
		return this;
	}

	public ExcelTemplate setCellVal(Calendar value) {
		newCell().setCellValue(value);
		return this;
	}

	public ExcelTemplate setCellVal(RichTextString value) {
		newCell().setCellValue(value);
		return this;
	}

	public ExcelTemplate setRowVals(List<String> value) {
		setRowVals(value.toArray(new String[] {}));
		return this;
	}

	public ExcelTemplate setRowVals(String[] value) {
		this.newRow();
		for (int i = 0; i < value.length; i++) {
			newCell().setCellValue(value[i]);
		}
		return this;
	}

	public ExcelTemplate replaceData(Map<String, String> map) {
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() != Cell.CELL_TYPE_STRING)
					continue;
				String val = cell.getStringCellValue().trim();
				if (val.startsWith("#") && map.containsKey(val.substring(1))) {
					cell.setCellValue(map.get(val.substring(1)));
				}
			}
		}
		return this;
	}

	public void writeToFile(String path) {
		try (OutputStream fos = new FileOutputStream(path)) {
			writeToStream(fos);
		} catch (FileNotFoundException e) {
			logger.error(path + "该文件路径不正确或者文件不存在，请检查", e);
		} catch (IOException e) {
			logger.error(path + "读取该文件异常，请检查", e);
		}
	}

	public void writeToStream(OutputStream os) {
		try {
			insertSerial();
			wb.write(os);
		} catch (IOException e) {
			logger.error("读取该文件异常，请检查", e);
		}
	}
}
