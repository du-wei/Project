package com.webapp.python;

import java.io.FileReader;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestPython {

	public static void main(String[] args) throws Exception, ScriptException {

		ScriptEngine engine = new ScriptEngineManager()
				.getEngineByName("python");

		// URL url = TestPython.class.getResource("/");
		// System.out.println(url.toString());
		// engine.eval(new FileReader("com/webapp/python/hello.py"));

		// PythonInterpreter python = new PythonInterpreter();
		// python.exec("import golb");
		// python.execfile("F:\\workspace\\webPython\\src\\hello.py");
		//
		// PyFunction func = (PyFunction)python.get("getDir", PyFunction.class);
		//
		// String path = "F:\\test\\*";
		// PyObject pyobj = func.__call__(new PyString(path));
		//
		// System.out.println("anwser = " + pyobj.toString());
	}

}
