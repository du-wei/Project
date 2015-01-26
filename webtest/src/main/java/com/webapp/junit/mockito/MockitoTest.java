package com.webapp.junit.mockito;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

public class MockitoTest {

	@Mock
	MockitoTest mTest;
	
	public String getStr() {
		return "hello world";
	}

	@Test
	public void mockitoTest() {
		MockitoTest test = mock(MockitoTest.class);
		when(test.getStr()).thenReturn("what");

		System.out.println(test.getStr());
		System.out.println(test.getStr());
		verify(test, atLeast(1));

	}

}
