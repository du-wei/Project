package com.webapp.utils.base;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.webapp.utils.collectionns.ListUtilsTest;
import com.webapp.utils.datasort.DataSortUtilsTest;
import com.webapp.utils.date.DateToolsTest;
import com.webapp.utils.enums.EnumUtilsTest;
import com.webapp.utils.format.FmtUtilsTest;
import com.webapp.utils.json.JSONUtilsTest;
import com.webapp.utils.random.RandomUtilsTest;
import com.webapp.utils.string.UtilsTest;


@RunWith(Suite.class)						//指定运行器
@SuiteClasses({
	DataSortUtilsTest.class,
	DateToolsTest.class,
//	EmailUtilsTest.class,
	EnumUtilsTest.class,
	FmtUtilsTest.class,
	JSONUtilsTest.class,
	RandomUtilsTest.class,
	UtilsTest.class,
	ListUtilsTest.class
})
public class JunitTestAll {
}
