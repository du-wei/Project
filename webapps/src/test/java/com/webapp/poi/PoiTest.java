package com.webapp.poi;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.webapp.utils.poi.ExcelHeader;
import com.webapp.utils.poi.ExcelUtils;

public class PoiTest {

    // @Test
    public void test() {
	ExcelTemplate et = ExcelTemplate.getInstance().readTemplateByClasspath("/test.xls");
	et.newRow();
	et.setCellVal("11").setCellVal("11").setCellVal("11");
	et.newRow();
	et.setCellVal(1).setCellVal(true).setCellVal(new Date());

	Map<String, String> map = new HashMap<String, String>();
	map.put("title", "ok");
	map.put("date", "2022-2-2");
	et.replaceData(map);

	et.writeToFile("ok.xls");
    }

    @Test
    public void testTmp() {
    }
}
