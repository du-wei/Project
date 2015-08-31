package com.webapp.remoting.service.http.spring;

public class HttpSpringService {

	public static void main(String[] args) {
		String url = "httpService";
		HttpSpringUtils.pubHttpService(IHttp.class, new HttpImpl(), url);
	}

}
