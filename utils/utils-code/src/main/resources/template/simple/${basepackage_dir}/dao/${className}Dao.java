${gg.setOverride(true)}
<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

/**
 * @description: The automatically generated ${className}Dao
 * @Package  ${basepackage}.dao
 * @Date	 ${.now?string("yyyy-MM-dd HH:mm:ss")}
 * @version  V1.0
 */
public interface ${className}Dao {

	public enum ${className}Enum{
		<#list table.columns as column>${column}<#if column_has_next>,</#if></#list>
	}

	String key = "key";
	String val = "val";
	String table = "${table.sqlName}";
	String selCols = "<#list table.columns as column>${column.sqlName}<#if column.columnNameLower != column.sqlName> ${column.columnNameLower}</#if><#if column_has_next>,</#if></#list>";
	String insCols = "<#list table.columns as column>${column.sqlName}<#if column_has_next>,</#if></#list>";
	String insVals = "<#list table.columns as column>${'#{'}${column.columnNameLower}${'}'}<#if column_has_next>,</#if></#list>";
	String updCols = "<#list table.notPkColumns as column>${column.sqlName}=<@mapperEl column.columnNameFirstLower/><#if column_has_next>,</#if></#list>";
	String insMulVals = "<#list table.columns as column>${'#{'}it.${column.columnNameLower}${'}'}<#if column_has_next>,</#if></#list>";

	<#list table.columns as column>
	<#if column.pk>
	/** select by pk ${column.sqlName} **/
	@Select("select " + selCols + " from " + table + " where <@pkwhere/>")
	public ${className} getBy${column.columnName}(<@pkparam/>);

	/** delete by pk ${column.sqlName} **/
	@Delete("delete from " + table + " where <@pkwhere/>")
    public boolean delBy${column.columnName}(<@pkparam/>);

    /** update by pk ${column.sqlName} **/
    @Update("update " + table + " set " + updCols + " where <@pkwhere/>")
	public boolean updBy${column.columnName}(${className} ${classNameLower});

	</#if>
	<#if column.unique && !column.pk>
	/** select by unique ${column.sqlName} **/
	@Select("select " + selCols + " from " + table + " where ${column.sqlName}=${'#{'}${column.columnNameLower}${'}'}")
	public ${className} getBy${column.columnName}(${column.simpleJavaType} ${column.columnNameLower});

	/** delete by unique ${column.sqlName} **/
	@Delete("delete from " + table + " where ${column.sqlName}=${'#{'}${column.columnNameLower}${'}'}")
    public boolean delBy${column.columnName}(${column.simpleJavaType} ${column.columnNameLower});

    /** update by unique ${column.sqlName} **/
    @Update("update " + table + " set " + updCols + " where ${column.sqlName}=${'#{'}${column.columnNameLower}${'}'}")
	public boolean updBy${column.columnName}(${className} ${classNameLower});

	</#if>
	</#list>

	/** count all record **/
	@Select("select count(1) from " + table)
	public Integer count();

	/** count by where key=val **/
	@SelectProvider(type=SqlProvider.class, method="countByKV")
	public Integer countByKV(@Param(key)Enum${className} key, @Param(val)Object val);

	/** count by where key1=val1 and key2=val2 ... **/
	@SelectProvider(type=SqlProvider.class, method="countByMap")
	public Integer countByMap(Map<String, Object> params);

	/** select all record **/
	@Select("select " + selCols + " from " + table)
	public List<${className}> getAll();

	/** select List<${className}> by where key=val **/
	@SelectProvider(type=SqlProvider.class, method="getByKV")
	public List<${className}> getByKV(@Param(key)Enum${className} key, @Param(val)Object val);

	/** select List<${className}> by where key1=val1 and key2=val2 ... **/
	@SelectProvider(type=SqlProvider.class, method="getByMap")
	public List<${className}> getByMap(Map<String, Object> params);

	/** insert a ${className} **/
	@Insert("insert into " + table + "(" + insCols + ") values(" + insVals + ")")
	@SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=Integer.class)
    public boolean add(${className} ${classNameLower});

	/** insert List<${className}> **/
	@Insert("<script>insert into " + table + " (" + insCols + ") values "
            + "<foreach item='it' collection='list' separator=','>(" + insMulVals + ")</foreach></script>")
	public boolean adds(@Param("list")List<${className}> list);

	/** delete by where key=val **/
	@DeleteProvider(type=SqlProvider.class, method="delByKV")
    public boolean delByKV(@Param(key)Enum${className} key, @Param(val)Object val);

	/** delete by where key1=val1 and key2=val2 ... **/
	@DeleteProvider(type=SqlProvider.class, method="delByMap")
    public boolean delByMap(Map<String, Object> params);


	static class SqlProvider {
		public String getByKV(Map<String, Object> param){
			return new SQL().SELECT(selCols).FROM(table).WHERE(param.get(key) + "=${'#{'}" + val + "${'}'}").toString();
		}
		public String getByMap(Map<String, Object> params){
			Iterator<String> keys = params.keySet().iterator();
			String sql = new SQL(){{
				SELECT(selCols);
				FROM(table);
				for( ;keys.hasNext(); ){
					String key = keys.next();
					WHERE(key + "=" + params.get(key));
				}
			}}.toString();
			return sql;
		}
		public String delByKV(Map<String, Object> param){
			return new SQL().DELETE_FROM(table).WHERE(param.get(key) + "=${'#{'}" + val + "${'}'}").toString();
		}
		public String delByMap(Map<String, Object> params){
			Iterator<String> keys = params.keySet().iterator();
			String sql = new SQL(){{
				DELETE_FROM(table);
				for( ;keys.hasNext(); ){
					String key = keys.next();
					WHERE(key + "=" + params.get(key));
				}
			}}.toString();
			return sql;
		}
		public String countByKV(Map<String, Object> param){
			return new SQL().SELECT("count(1)").FROM(table).WHERE(param.get(key) + "=${'#{'}" + val + "${'}'}").toString();
		}
		public String countByMap(Map<String, Object> params){
			Iterator<String> keys = params.keySet().iterator();
			String sql = new SQL(){{
				SELECT("count(1)");
				FROM(table);
				for( ;keys.hasNext(); ){
					String key = keys.next();
					WHERE(key + "=" + params.get(key));
				}
			}}.toString();
			return sql;
		}
	}
}
