package com.webapp.template.utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassCompiler {

	private static final Logger logger = LoggerFactory.getLogger(ClassCompiler.class);

	public static Class<?> compile(String name, String content) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.SIMPLIFIED_CHINESE, Charset.defaultCharset());

        List<String> options = Arrays.asList("-d", getOutDir());
        CompilationTask task = compiler.getTask(null, fileManager, null, options, null, Arrays.asList(new JavaObject(name, content)));
        boolean result = task.call();
        if (result == true) {
            logger.info("Compile it successfully.");
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                logger.error("", e);
            }
        }
        return null;
    }


	private static String getOutDir() {
		String outDir = "";
		try {
			File classPath = new File(ClassCompiler.class.getResource("/").toURI());
            outDir = classPath.getAbsolutePath() + File.separator;

            logger.info("out dir --> {}", outDir);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
		return outDir;
	}

    private static class JavaObject extends SimpleJavaFileObject {
        private String content;
        public JavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }

}
