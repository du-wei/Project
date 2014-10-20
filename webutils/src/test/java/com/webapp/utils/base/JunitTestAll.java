package com.webapp.utils.base;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.webapp.utils.datasort.DataSortUtilsTest;


@RunWith(Suite.class)						//指定运行器
@SuiteClasses({
	DataSortUtilsTest.class
})
public class JunitTestAll {
}
