package com.webapp.junit.httpunit;

import java.net.MalformedURLException;
import java.net.URL;

import com.meterware.httpunit.cookies.CookieSource;

public class HttpUnitUtils {

	public static CookieSource getCookieSource(String sourceURL, String header) {
		return HttpUnitUtils
				.getCookieSource(sourceURL, new String[] { header });
	}

	public static CookieSource getCookieSource(String sourceURL,
			String[] headers) {
		try {
			return new TestSource(new URL(sourceURL), headers);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * create a TestSource for Cookies
	 */
	private static class TestSource implements CookieSource {

		private URL _sourceURL;
		private String[] _headers;

		/**
		 * construct a TestSource form a single header string
		 * 
		 * @param sourceURL
		 * @param header
		 */
		public TestSource(URL sourceURL, String header) {
			this(sourceURL, new String[] { header });
		}

		public TestSource(URL sourceURL, String[] headers) {
			_sourceURL = sourceURL;
			_headers = headers;
		}

		public URL getURL() {
			return _sourceURL;
		}

		public String[] getHeaderFields(String fieldName) {
			return fieldName.equalsIgnoreCase("set-cookie") ? _headers
					: new String[0];
		}
	}

}
