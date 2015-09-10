package com.webapp.arithmetic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Gawk {

	public static void main(String[] args) throws Exception {

		String path = "com/webapp/arithmetic/script.sh";

		Runtime run = Runtime.getRuntime();
		Process p = run.exec("sh E://data1/xx.sh E://data1/xx.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(
		        p.getInputStream()));

		String s = "";
		while ((s = reader.readLine()) != null) {
			System.out.println(s);
		}
		reader.close();
	}

	/** Prevent instantiation */
	private Gawk() {

	}

}
