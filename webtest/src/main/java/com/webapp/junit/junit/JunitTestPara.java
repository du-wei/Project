package com.webapp.junit.junit;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class JunitTestPara {

	private int para;
	private String code;

	public JunitTestPara(int para, String code) {
		this.para = para;
		this.code = code;
	}

	@Parameters
	public static Collection<?> data() {
		return Arrays.asList(new Object[][] { { 1, "a" }, { 2, "b" },
				{ 3, "c" }, { 4, "d" } });
	}

	@Test
	public void para() {
		System.out.println(this.para + " - " + this.code);
	}

}
