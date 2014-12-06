package com.webapp.mvc;

import static org.junit.Assert.*;

import java.util.ResourceBundle;

import org.junit.Test;
import org.springframework.context.support.MessageSourceResourceBundle;

public class Msg {
		
	@Test
    public void testName() throws Exception {
		ResourceBundle bundle = MessageSourceResourceBundle.getBundle("pool");
		String string = bundle.getString("maxIdle");
		System.out.println(string);
    }
	
}
