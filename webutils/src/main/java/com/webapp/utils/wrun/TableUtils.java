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

import com.webapp.utils.model.Student;
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

	private static class TableRule {
		private String tableName;
		private String pkg;
		private int defStrWidth;
		private int defIntWidth;
		private String tableId;
		private boolean snakeMap;
		private List<String> uniqueCol = new ArrayList<>();
		private Map<String, Integer> colWidth = new HashMap<>();
		private boolean hasGetSet;

		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public String getPkg() {
			return pkg;
		}
		public void setPkg(String pkg) {
			this.pkg = pkg;
		}
		public int getDefStrWidth() {
			return defStrWidth;
		}
		public void setDefStrWidth(int defStrWidth) {
			this.defStrWidth = defStrWidth;
		}
		public int getDefIntWidth() {
			return defIntWidth;
		}
		public void setDefIntWidth(int defIntWidth) {
			this.defIntWidth = defIntWidth;
		}
		public String getTableId() {
			return tableId;
		}
		public void setTableId(String tableId) {
			this.tableId = tableId;
		}
		public List<String> getUniqueCol() {
			return uniqueCol;
		}
		public void setUniqueCol(List<String> uniqueCol) {
			this.uniqueCol = uniqueCol;
		}
		public boolean isSnakeMap() {
			return snakeMap;
		}
		public void setSnakeMap(boolean snakeMap) {
			this.snakeMap = snakeMap;
		}
		public Map<String, Integer> getColWidth() {
			return colWidth;
		}
		public void setColWidth(Map<String, Integer> colWidth) {
			this.colWidth = colWidth;
		}
		public boolean isHasGetSet() {
			return hasGetSet;
		}
		public void setHasGetSet(boolean hasGetSet) {
			this.hasGetSet = hasGetSet;
		}


		public void addColWidth(String col, int width) {
			this.colWidth.put(col, width);
		}
	}

	private final static String tab = "\t";
	private final static String enter = "\n";
	private Class<T> clz;
	private TableRule tableRule;
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
		tableRule = new TableRule();
		tableRule.setDefIntWidth(11);
		tableRule.setDefStrWidth(255);
		tableRule.setSnakeMap(true);
		tableRule.setHasGetSet(false);
		tableRule.setPkg("com.webapp.table");
		tableRule.setTableName(Utils.toSnake(clz.getSimpleName()));
		this.clz = clz;
	}

	public static <T> TableUtils<T> of(Class<T> clz) {
		return new TableUtils<T>(clz);
	}

	public TableUtils<T> table(String table){
		tableRule.setTableName(table);
		return this;
	}
	public TableUtils<T> id(String col){
		tableRule.setTableId(col);
		return this;
	}
	public TableUtils<T> unique(String ...col) {
		tableRule.setUniqueCol(Arrays.asList(col));
		return this;
	}

	public TableUtils<T> camel() {
		tableRule.setSnakeMap(false);
		return this;
	}
	public TableUtils<T> snake() {
		tableRule.setSnakeMap(true);
		return this;
	}
	public TableUtils<T> width(String col, int width) {
		tableRule.addColWidth(col, width);
		return this;
	}
	public TableUtils<T> width(Map<String, Integer> cloWidth) {
		tableRule.setColWidth(cloWidth);
		return this;
	}

	public void done() {
		StringBuffer columns = new StringBuffer();
		StringBuffer getset = new StringBuffer();
		List<Field> fieldList = new ArrayList<>();

		String name = clz.getSimpleName();

		columns.append("package " + tableRule.getPkg() + ";").append(enter);
		columns.append("import org.nutz.dao.entity.annotation.*;").append(enter);
		columns.append("@Table(\"" + tableRule.getTableName() + "\")").append(enter);
		columns.append("public class " + name + "{").append(enter);

		Class<? super T> superclz = clz.getSuperclass();
		if(superclz != null){
			fieldList.addAll(Arrays.asList(superclz.getDeclaredFields()));
		}
        fieldList.addAll(Arrays.asList(clz.getDeclaredFields()));
        Field[] fields = fieldList.toArray(new Field[]{});

        for(Field field : fields){
            String col = field.getName();
            String type = field.getType().getSimpleName();

            if(col.equals(tableRule.getTableId())){
            	columns.append(tab).append("@Id").append(enter);
            }
            if(tableRule.getUniqueCol().contains(col)){
            	columns.append(tab).append("@Name").append(enter);
            }
            if(tableRule.isSnakeMap()){
            	columns.append(tab).append("@Column(hump = true)").append(enter);
            }

            if(tableRule.getColWidth().containsKey(col)){
            	columns.append(tab).append("@ColDefine(width=" + tableRule.getColWidth().get(col) + ")").append(enter);
            }else{
            	if(type.equals(String.class.getSimpleName())){
            		columns.append(tab).append("@ColDefine(width=" + tableRule.getDefStrWidth() + ")").append(enter);
            	}
            }
            columns.append(tab).append("private " + type + " " + col + ";").append(enter);

            getset.append(tab).append("public " + type + " get" + Utils.toPascal(col) + "(){").append(enter);
            getset.append(tab).append("\treturn " + col + ";").append(enter);
            getset.append(tab).append("}").append(enter);

            getset.append(tab).append("public void set" + Utils.toPascal(col) + "(" + type + " " + col + "){").append(enter);
            getset.append(tab).append("\tthis." + col + " = " + col + ";").append(enter);
            getset.append(tab).append("}").append(enter);
        }
        if(tableRule.isHasGetSet()) columns.append(getset);
        columns.append("}");

        System.out.println(columns.toString());
        createTable(columns, name);
	}

	private void createTable(StringBuffer columns, String name) {
		Class<?> compile = compile(tableRule.getPkg() + "." + name ,columns.toString(), "");
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

    private static Class<?> compile(String name, String content, String outDir) {
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

    	TableUtils.of(Student.class).id("id").unique("stuNo").snake().done();

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
