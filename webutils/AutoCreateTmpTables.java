package com.ucf.meng.datacenter.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import com.ucf.meng.datacenter.model.pub.AdminLicenseInfo;
import com.webapp.utils.string.Utils;

/**
 *
 * ref: http://nutzam.com/core/dao/hello.html
 *
 * http://nutzam.com/core/appendix/create_datasource.html
 *
 * https://github.com/nutzam/nutz/blob/master/doc/manual/dao/entity.man
 *
 *
 * @author chunfeng
 *
 */

class TableUtils<T> {

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

            getset.append("\tpublic void set" + Utils.toPascal(col) + "("+type+" " +col+ "){ \n"
            			+ "\t\tthis." + col + " = " + col + ";"
            			+ "\n\t}\n");
        }
        columns.append(getset);
        columns.append("}");

        Class<?> compile = compile(pkg + "." + name ,columns.toString(), "");
		Dao dao = new NutDao(ds);
		dao.create(compile, true);
	}

    private final static Class<?> compile(String name, String content, String outDir) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StrSrcJavaObject srcObject = new StrSrcJavaObject(name, content);
        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
        String flag = "-d";
        try {
            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            outDir = classPath.getAbsolutePath() + File.separator;
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        Iterable<String> options = Arrays.asList(flag, outDir);
        CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
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
    private static class StrSrcJavaObject extends SimpleJavaFileObject {
        private String content;
        public StrSrcJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }

}
public class AutoCreateTmpTables {

	public AutoCreateTmpTables() {

	}

	public static void main(String[] argv) {
		// Daos.createTablesInPackage(dao, "net.wendal.nutzbook.bean", true);



		Map<String, String> ds = new HashMap<>();
		ds.put("driver", "com.mysql.jdbc.Driver");
		ds.put("url", "jdbc:mysql://10.20.69.225:3306/chunfeng_test?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&autoReconnect=true");
		ds.put("username", "root");
		ds.put("password", "1q2w3e4r");


//		Dao dao = new NutDao(ds);
		TableUtils.setDataSource(ds);

		TableUtils.of(AdminLicenseInfo.class).unique("regNo").table("ok").id("id").width("regNo", 50).underline().done();

//		dao.create(AdminLicenseInfo.class, true);
//		dao.create(AdminPenaltyInfo.class, true);
//
//		dao.create(EntIpPledge.class, true);
//		dao.create(EntPenalty.class, true);
//		dao.create(EntShareholderChange.class, true);
//		dao.create(EntStockEquityChange.class, true);
//		dao.create(Yearbook.class, true);
//
//		dao.create(RepBasicPub.class, true);
//		dao.create(RepEntAssertPub.class, true);
//		dao.create(RepEntAssurePub.class, true);
//		dao.create(RepEntInvestPub.class, true);
//		dao.create(RepModifyPub.class, true);
//		dao.create(RepShareholderCapPub.class, true);
//		dao.create(RepWebsitePub.class, true);
//
//		dao.create(IcAbnormalInfo.class, true);
//		dao.create(IcIllegalInfo.class, true);
//		dao.create(IcMortgageInfo.class, true);
//		dao.create(IcSharePawneeInfo.class, true);
//		dao.create(IcSpotCheckInfo.class, true);
//
//		dao.create(Branch.class, true);
//		dao.create(Liquidation.class, true);
//		dao.create(MainMember.class, true);
//
//		dao.create(RegChangeRecord.class, true);
//		dao.create(RegDetail.class, true);
//		dao.create(RegShareholder.class, true);

		// ds.close(); // 这个DataSource不是一个连接池,所以关不关都行
	}

}
