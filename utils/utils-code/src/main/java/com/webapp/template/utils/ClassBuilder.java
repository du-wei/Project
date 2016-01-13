package com.webapp.template.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.utils.string.Utils;
import com.webapp.utils.string.Utils.Symbol;

public class ClassBuilder<T> {

	private static final Logger logger = LoggerFactory.getLogger(ClassBuilder.class);
	private static final String pkg = "com.webapp.table";
	private Class<T> clz;
	private TableRule tableRule;

	private ClassBuilder(Class<T> clz){
		tableRule = new TableRule();
		tableRule.setDefIntWidth(11);
		tableRule.setDefStrWidth(255);
		tableRule.setSnakeMap(true);
		tableRule.setHasGetSet(false);
		tableRule.setPkg(pkg);
		tableRule.setTableName(Utils.toSnake(clz.getSimpleName()));
		tableRule.setClzName(clz.getSimpleName());
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
		tableRule.setColWidth(cloWidth);
		return this;
	}

	private String createClz(){
		StringBuffer clzStr = new StringBuffer();
		StringBuffer getset = new StringBuffer();
		List<Field> fieldList = new ArrayList<>();

		String name = clz.getSimpleName();

		clzStr.append("package " + tableRule.getPkg() + ";").append(Symbol.Enter);
		clzStr.append("import org.nutz.dao.entity.annotation.*;").append(Symbol.Enter);
		clzStr.append("@Table(\"" + tableRule.getTableName() + "\")").append(Symbol.Enter);
		clzStr.append("public class " + name + "{").append(Symbol.Enter);

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
	        	clzStr.append(Symbol.Tab).append("@Id").append(Symbol.Enter);
	        }
	        if(tableRule.getUniqueCol().contains(col)){
	        	clzStr.append(Symbol.Tab).append("@Name").append(Symbol.Enter);
	        }
	        if(tableRule.isSnakeMap()){
	        	clzStr.append(Symbol.Tab).append("@Column(hump = true)").append(Symbol.Enter);
	        }

	        if(tableRule.getColWidth().containsKey(col)){
	        	clzStr.append(Symbol.Tab).append("@ColDefine(width=" + tableRule.getColWidth().get(col) + ")").append(Symbol.Enter);
	        }else{
	        	if(type.equals(String.class.getSimpleName())){
	        		clzStr.append(Symbol.Tab).append("@ColDefine(width=" + tableRule.getDefStrWidth() + ")").append(Symbol.Enter);
	        	}
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

	    logger.info(clzStr.toString());
		return clzStr.toString();
	}

	public void done(TableBuilder<T> builder) {
		String clzStr = createClz();
        builder.createTable(clzStr, tableRule.getPkg(), tableRule.getClzName());
	}

	private static class TableRule {
		private String tableName;
		private String clzName;
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

}
