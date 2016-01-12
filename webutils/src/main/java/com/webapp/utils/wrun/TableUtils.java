package com.webapp.utils.wrun;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.webapp.utils.string.Utils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;

public class TableUtils<T> {

	private enum Table {
		def_str_width,
		def_int_width,

		table_name,
		id_col,
		unique_col,
		map_type,
		width
	}

	private Class<T> clz;
	private Map<String, String> prop = new HashMap<>();

	private static String pkg = "com.webapp.table";
	private static SimpleDataSource ds;
	public static void setDataSource(Map<String, String> map) {
        ds = new SimpleDataSource();
		try {
			ds.setDriverClassName(map.get("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl(map.get("url"));
		ds.setUsername(map.get("username"));
		ds.setPassword(map.get("password"));
	}

	private TableUtils(Class<T> clz){
		prop.put(Table.def_str_width.name(), "255");
		prop.put(Table.def_int_width.name(), "11");
		this.clz = clz;
	}

	public static <T> TableUtils<T> of(Class<T> clz) {
		return new TableUtils<T>(clz);
	}

	public TableUtils<T> table(String table){
		prop.put(Table.table_name.name(), table);
		return this;
	}
	public TableUtils<T> id(String col){
		prop.put(Table.id_col.name(), col);
		return this;
	}
	public TableUtils<T> unique(String ...col) {
		prop.put(Table.unique_col.name(), Utils.split(Arrays.asList(col), "-"));
		return this;
	}

	public TableUtils<T> camel() {
		prop.put(Table.map_type.name(), "camel");
		return this;
	}
	public TableUtils<T> underline() {
		prop.put(Table.map_type.name(), "underline");
		return this;
	}
	public TableUtils<T> width(String col, int width) {
		prop.put(Table.width + col, String.valueOf(width));
		return this;
	}

	public void done() {
		StringBuffer columns = new StringBuffer();
		StringBuffer getset = new StringBuffer();
		List<Field> fieldList = new ArrayList<>();
		List<String> uniques = new ArrayList<>();

		String name = clz.getSimpleName();

		columns.append("package " + pkg + ";\n");
		columns.append("import org.nutz.dao.entity.annotation.*;\n");
		if(prop.containsKey(Table.table_name.name())){
			columns.append("@Table(\"" + prop.get(Table.table_name.name()) + "\")\n");
		}

		if(prop.containsKey(Table.unique_col.name())){
        	String unique = prop.get(Table.unique_col.name());
        	uniques = Arrays.asList(unique.split("-"));
        }
		columns.append("public class " + name + "{\n");

		Class<? super T> superclz = clz.getSuperclass();
		if(superclz != null){
			fieldList.addAll(Arrays.asList(superclz.getDeclaredFields()));
		}
        fieldList.addAll(Arrays.asList(clz.getDeclaredFields()));
        Field[] fields = fieldList.toArray(new Field[]{});

        for(Field field : fields){
            String col = field.getName();
            String type = field.getType().getSimpleName();

            if(col.equals(prop.get(Table.id_col.name()))){
            	columns.append("\t@Id\n");
            }
            if(prop.get(Table.map_type.name()).equals("underline")){
            	columns.append("\t@Column(hump = true)\n");
            }

            if(prop.containsKey(Table.width.name() + col)){
            	columns.append("\t@ColDefine(width=" + prop.get(Table.width.name() + col) + ")\n");
            }else{
            	if(type.equals(String.class.getSimpleName())){
            		columns.append("\t@ColDefine(width=" + prop.get(Table.def_str_width.name()) + ")\n");
            	}
            }

//            if(type.equals(Integer.class.getSimpleName()) || type.equals("int")){
//            	columns.append("\t@ColDefine(width=11)\n");
//            }

            if(uniques.contains(col)){
            	columns.append("\t@Name\n");
            }

            columns.append("\tprivate " + type + " " + col + ";\n");

            getset.append("\tpublic " + type + " get" + Utils.toPascal(col) + "(){ \n"
            			+ "\t\treturn " + col + ";"
            			+ "\n\t}\n");

            getset.append("\tpublic void set" + Utils.toPascal(col) + "(" + type + " " + col + "){ \n"
            			+ "\t\tthis." + col + " = " + col + ";"
            			+ "\n\t}\n");
        }
        columns.append(getset);
        columns.append("}");

        Class<?> compile = compile(pkg + "." + name ,columns.toString(), "");
		Dao dao = new NutDao(ds);
		dao.create(compile, true);
	}

	private static String getOutDir(String outDir) {
		try {
            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            if(StringUtils.isNotEmpty(outDir))
            	outDir = classPath.getAbsolutePath() + File.separator;

            System.out.println("out dir --> " + outDir);
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
		return outDir;
	}

    private final static Class<?> compile(String name, String content, String outDir) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, Locale.SIMPLIFIED_CHINESE, Charset.defaultCharset());

        List<String> options = Arrays.asList("-d", getOutDir(outDir));
        CompilationTask task = compiler.getTask(null, fileManager, null, options, null, Arrays.asList(new JavaObject(name, content)));
        boolean result = task.call();
        if (result == true) {
            System.out.println("Compile it successfully.");
            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
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

    public static void main(String[] args) throws Exception {

//    	cglib();
//
//    	javassist();
//
//		asm();

	}

	private static void cglib() {
//    	BeanMap props = BeanMap.create(bean);

    	HashMap<String, Class<?>> props = new HashMap<>();
		props.put("id", Integer.class);
		props.put("name", String.class);
		props.put("address", String.class);
    	BeanGenerator beanGen = new BeanGenerator();
    	beanGen.addProperty("id", Integer.class);

    	String string = beanGen.toString();

    	Class<?> createClass = (Class<?>)beanGen.createClass();

    	String string2 = createClass.toString();
    	System.out.println(string2);


    	Enhancer jj = new Enhancer();
	}

	private static void javassist() {
		ClassPool clzPool = ClassPool.getDefault();
    	CtClass cc = clzPool.makeClass("com.webapp.utils.Ok");
        CtMethod method;
		try {

			CtField cf = CtField.make("String ff;", cc);
			cc.addField(cf);

			method = CtNewMethod.make("public void code(){}", cc);
			method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
			cc.addMethod(method);

			cc.debugWriteFile();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		}
	}

	private static void asm() throws Exception {
		ClassWriter classWriter = new ClassWriter(0);
        // 通过visit方法确定类的头部信息
        classWriter.visit(Opcodes.V1_8,// java版本
                Opcodes.ACC_PUBLIC,// 类修饰符
                "Programmer", // 类的全限定名
                null, "java/lang/Object", null);

        // 定义code方法
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V",
                null, null);
        classWriter.visitEnd();
        // 使classWriter类已经完成
        // 将classWriter转换成字节数组写到文件里面去
        byte[] data = classWriter.toByteArray();
        File file = new File("D://xxxx.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();

	}

}
