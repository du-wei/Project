package com.webapp.template.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.utils.string.Utils;
import com.webapp.utils.string.Utils.Symbol;

public class ClassBuilder<T> {

	private static final Logger logger = LoggerFactory.getLogger(ClassBuilder.class);
	private static final String DEF_PKG = "com.webapp.table";
	private Class<T> clz;
	private TableRule tableRule;

	private ClassBuilder(Class<T> clz){
		tableRule = new TableRule(clz);
		this.clz = clz;
	}

	public static <T> ClassBuilder<T> of(Class<T> clz) {
		return new ClassBuilder<T>(clz);
	}

	public ClassBuilder<T> table(String table){
		tableRule.setTableName(table);
		return this;
	}
	public ClassBuilder<T> id(String col){
		tableRule.setTableId(col);
		return this;
	}
	public ClassBuilder<T> unique(String ...col) {
		tableRule.setUniqueCol(Arrays.asList(col));
		return this;
	}
	public ClassBuilder<T> notNull(String ...col){
		tableRule.addNotNull(Arrays.asList(col));
		return this;
	}

	public ClassBuilder<T> camel() {
		tableRule.setSnakeMap(false);
		return this;
	}
	public ClassBuilder<T> snake() {
		tableRule.setSnakeMap(true);
		return this;
	}
	public ClassBuilder<T> width(String col, int width) {
		tableRule.addColWidth(col, width);
		return this;
	}
	public ClassBuilder<T> width(Map<String, Integer> cloWidth) {
		tableRule.addColWidth(cloWidth);
		return this;
	}
	public ClassBuilder<T> precision(String col, int precision){
		tableRule.addPrecision(col, precision);
		return this;
	}
	public ClassBuilder<T> precision(Map<String, Integer> precision){
		tableRule.addPrecision(precision);
		return this;
	}
	public ClassBuilder<T> defVal(String col, String val){
		tableRule.addDefVal(col, val);
		return this;
	}
	public ClassBuilder<T> defVal(Map<String, String> defVal){
		tableRule.setDefVal(defVal);
		return this;
	}

	private String createClz(){
		Map<String, String> ipt = new HashMap<>();
		StringBuffer topStr = new StringBuffer();
		StringBuffer clzStr = new StringBuffer();
		StringBuffer getset = new StringBuffer();

		String name = clz.getSimpleName();

		topStr.append("package " + tableRule.getPkg() + ";").append(Symbol.Enter);
		topStr.append("import org.nutz.dao.entity.annotation.*;").append(Symbol.Enter);
		clzStr.append("@Table(\"" + tableRule.getTableName() + "\")").append(Symbol.Enter);
		clzStr.append("public class " + name + "{").append(Symbol.Enter);

		Field[] fields = getAllField(clz);

	    for(Field field : fields){
	        String col = field.getName();
	        String type = field.getType().getSimpleName();
	        String typePkg = field.getType().getName();

	        if(!typePkg.startsWith("java.lang") && typePkg.contains(Symbol.Dot)){
	        	ipt.put(type, typePkg);
	        }

	        if(col.equals(tableRule.getTableId())){
	        	clzStr.append(Symbol.Tab).append("@Id").append(Symbol.Enter);
	        }
	        if(tableRule.getUniqueCol().contains(col)){
	        	clzStr.append(Symbol.Tab).append("@Name").append(Symbol.Enter);
	        }
	        if(tableRule.isSnakeMap()){
	        	clzStr.append(Symbol.Tab).append("@Column(hump = true)").append(Symbol.Enter);
	        }
        	if(tableRule.getDefVal().containsKey(col)){
        		String val = tableRule.getDefVal().get(col);
        		clzStr.append(Symbol.Tab).append("@Default(value = \"" + val + "\")").append(Symbol.Enter);
        	}

        	ColDefineRule cr = tableRule.getColRule().get(col);
        	String colDefine = "";
        	if(cr.getWidth() != 0){
        		colDefine += "width=" + cr.getWidth();
        	}
        	if(cr.getPrecision() != 0){
        		colDefine += ",precision=" + cr.getPrecision();
        	}
        	if(cr.isNotNull()){
        		colDefine += ",notNull=true";
        	}
        	if(StringUtils.isNotEmpty(colDefine)){
        		colDefine = "@ColDefine(" + colDefine + ")";
        		clzStr.append(Symbol.Tab).append(colDefine).append(Symbol.Enter);
        	}

	        clzStr.append(Symbol.Tab).append("private " + type + " " + col + ";").append(Symbol.Enter);

	        getset.append(Symbol.Tab).append("public " + type + " get" + Utils.toPascal(col) + "(){").append(Symbol.Enter);
	        getset.append(Symbol.Tab).append("\treturn " + col + ";").append(Symbol.Enter);
	        getset.append(Symbol.Tab).append("}").append(Symbol.Enter);

	        getset.append(Symbol.Tab).append("public void set" + Utils.toPascal(col) + "(" + type + " " + col + "){").append(Symbol.Enter);
	        getset.append(Symbol.Tab).append("\tthis." + col + " = " + col + ";").append(Symbol.Enter);
	        getset.append(Symbol.Tab).append("}").append(Symbol.Enter);
	    }
	    if(tableRule.isHasGetSet()) clzStr.append(getset);
	    clzStr.append("}");

	    ipt.forEach((k,v)->{
	    	topStr.append("import " + v + ";").append(Symbol.Enter);
	    });

	    String result = topStr.append(clzStr).toString();
		return result;
	}

	public void done(TableBuilder<T> builder) {
		String clzStr = createClz();
        builder.createTable(clzStr, tableRule.getPkg(), tableRule.getClzName());
	}

	public void view() {
		String clzStr = createClz();
		logger.info(Symbol.Enter + clzStr);
	}

	private static <T> Field[] getAllField(Class<T> clz) {
		List<Field> fieldList = new ArrayList<>();
		Class<? super T> superclz = clz.getSuperclass();
		if(superclz != null){
			fieldList.addAll(Arrays.asList(superclz.getDeclaredFields()));
		}
	    fieldList.addAll(Arrays.asList(clz.getDeclaredFields()));
	    Field[] fields = fieldList.toArray(new Field[]{});
		return fields;
	}
	private static Map<String, ColDefineRule> getColRuleMap(Field[] fields) {
		Map<String, ColDefineRule> colRule = new HashMap<>();
		for(Field field : fields){
	        String col = field.getName();
	        String type = field.getType().getSimpleName();
	        if(type.equals(String.class.getSimpleName())){
	        	colRule.put(col, new ColDefineRule(255, 0));
	        }else if(type.equals(Integer.class.getSimpleName())){
	        	colRule.put(col, new ColDefineRule(11, 0));
	        }else if(type.equals(Long.class.getSimpleName())){
	        	colRule.put(col, new ColDefineRule(0, 0));
	        }else if(type.equals(Date.class.getSimpleName())){
	        	colRule.put(col, new ColDefineRule(0, 0));
	        }else {
	        	colRule.put(col, new ColDefineRule(0, 0));
			}
		}
		return colRule;
	}

	private static class TableRule {
		private String tableName;
		private Field[] field;
		private String clzName;
		private String pkg;
//		private int defStrWidth;
//		private int defIntWidth;
		private String tableId;
		private boolean snakeMap;
		private List<String> uniqueCol = new ArrayList<>();
		private Map<String, String> defVal = new HashMap<>();
		private Map<String, ColDefineRule> colRule = new HashMap<>();
		private boolean hasGetSet;

		public void addDefVal(String col, String val) {
			this.defVal.put(col, val);
		}
		public void addPrecision(Map<String, Integer> precision) {
			precision.forEach((col, ps)->{
				colRule.get(col).setPrecision(ps);
			});
		}
		public void addPrecision(String col, int precision) {
			colRule.forEach((colDef, colRule)->{
				if(col.equals(colDef)) colRule.setPrecision(precision);
			});
		}
		public void addColWidth(String col, int width) {
			colRule.forEach((colDef, colRule)->{
				if(col.equals(colDef)) colRule.setWidth(width);
			});
		}
		public void addColWidth(Map<String, Integer> cloWidth) {
			cloWidth.forEach((col, width)->{
				colRule.get(col).setWidth(width);
			});
		}
		public void addNotNull(List<String> notNull) {
			notNull.forEach(col->colRule.get(col).setNotNull(true));
		}
		public <T> TableRule(Class<T> clz) {
			this.setField(getAllField(clz));
//			this.setDefIntWidth(11);
//			this.setDefStrWidth(255);
			this.setSnakeMap(true);
			this.setHasGetSet(false);
			this.setPkg(DEF_PKG);
			this.setTableName(Utils.toSnake(clz.getSimpleName()));
			this.setClzName(clz.getSimpleName());
			this.setColRule(getColRuleMap(this.getField()));
		}

		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public Field[] getField() {
			return field;
		}
		public void setField(Field[] field) {
			this.field = field;
		}
		public String getClzName() {
			return clzName;
		}
		public void setClzName(String clzName) {
			this.clzName = clzName;
		}
		public String getPkg() {
			return pkg;
		}
		public void setPkg(String pkg) {
			this.pkg = pkg;
		}
//		public int getDefStrWidth() {
//			return defStrWidth;
//		}
//		public void setDefStrWidth(int defStrWidth) {
//			this.defStrWidth = defStrWidth;
//		}
//		public int getDefIntWidth() {
//			return defIntWidth;
//		}
//		public void setDefIntWidth(int defIntWidth) {
//			this.defIntWidth = defIntWidth;
//		}
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
		public Map<String, String> getDefVal() {
			return defVal;
		}
		public void setDefVal(Map<String, String> defVal) {
			this.defVal = defVal;
		}
		public boolean isHasGetSet() {
			return hasGetSet;
		}
		public void setHasGetSet(boolean hasGetSet) {
			this.hasGetSet = hasGetSet;
		}
		public Map<String, ColDefineRule> getColRule() {
			return colRule;
		}
		public void setColRule(Map<String, ColDefineRule> colRule) {
			this.colRule = colRule;
		}
	}

	private static class ColDefineRule {
//		private boolean auto;
//		private ColType type;
		private int width;
		private int precision;
		private boolean notNull;
//		private String customType;

		public ColDefineRule(int width, int precision) {
//			this.auto = false;
			this.width = width;
			this.precision = precision;
			this.notNull = false;
		}
//		public boolean isAuto() {
//			return auto;
//		}
//		public void setAuto(boolean auto) {
//			this.auto = auto;
//		}
//		public ColType getType() {
//			return type;
//		}
//		public void setType(ColType type) {
//			this.type = type;
//		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getPrecision() {
			return precision;
		}
		public void setPrecision(int precision) {
			this.precision = precision;
		}
		public boolean isNotNull() {
			return notNull;
		}
		public void setNotNull(boolean notNull) {
			this.notNull = notNull;
		}
//		public String getCustomType() {
//			return customType;
//		}
//		public void setCustomType(String customType) {
//			this.customType = customType;
//		}
	}

}
