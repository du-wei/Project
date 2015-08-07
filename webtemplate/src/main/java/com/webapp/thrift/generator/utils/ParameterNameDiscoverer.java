/**
 * 
 */
package com.webapp.thrift.generator.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;

public class ParameterNameDiscoverer {
	
	Paranamer paranamer = new CachingParanamer();
	
	public String[] getParameterNames(Method arg0) {
		return paranamer.lookupParameterNames(arg0, false);
	}

	@SuppressWarnings("rawtypes")
	public String[] getParameterNames(Constructor arg0) {
		return paranamer.lookupParameterNames(arg0, false);
	}
}
