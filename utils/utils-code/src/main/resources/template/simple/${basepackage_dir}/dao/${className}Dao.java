${gg.setOverride(true)}
<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import ${basepackage}.model.${className};

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webapp.model.Linux;

/**
 * @description: The automatically generated ${className}Dao
 * @Package  ${basepackage}.dao
 * @Date	 ${.now?string("yyyy-MM-dd HH:mm:ss")}
 * @version  V1.0
 */
public interface ${className}Dao {

	public enum Enum${className}{
		<#list table.columns as column>${column}<#if column_has_next>,</#if></#list>
	}
	
	String selCols = "<#list table.columns as column>${column.sqlName}<#if column.columnNameLower != column.sqlName> ${column.columnNameLower}</#if><#if column_has_next>,</#if></#list>";
	String insCols = "<#list table.columns as column>${column.sqlName}<#if column_has_next>,</#if></#list>";
	String insVals = "<#list table.columns as column>${'#{'}${column.columnNameLower}${'}'}<#if column_has_next>,</#if></#list>";
	String updCols = "<#list table.notPkColumns as column>${column.sqlName}=<@mapperEl column.columnNameFirstLower/><#if column_has_next>,</#if></#list>";
	String insMulVals = "<#list table.columns as column>${'#{'}it.${column.columnNameLower}${'}'}<#if column_has_next>,</#if></#list>";
	
	@Select("select count(1) from ${table.sqlName}")
	public Integer count();
	
	@Select("select " + selCols + " from ${table.sqlName}")
	public List<${className}> getAll();
	
	@Select("select " + selCols + " from ${table.sqlName} where <@pkwhere/>")
	public ${className} getById(<@pkparam/>);
	
	@Select("select " + selCols + " from ${table.sqlName} where <@mapperEl 'key'/>=<@mapperEl 'val'/>")
	public List<${className}> getByKV(@Param("key")Enum${className} key, @Param("val")Object val);
	
	@Update("update ${table.sqlName} " + updCols + " where <@pkwhere/>")
	public boolean updById(${className} ${classNameLower});
	
	@Insert("insert into ${table.sqlName} (" + insCols + ") values(" + insVals + ")")
    public Integer add(${className} ${classNameLower});
	
	@Insert("<script>insert into ${table.sqlName} (" + insCols + ") values "
            + "<foreach item='it' collection='list' separator=','>(" + insMulVals + ")</foreach></script>")
	public boolean adds(@Param("list")List<${className}> list);
	
	@Delete("delete from ${table.sqlName} where <@pkwhere/>")
    public boolean delById(<@pkparam/>);
	
	@Delete("delete from ${table.sqlName} where <@mapperEl 'key'/>=<@mapperEl 'val'/>")
    public boolean delByKV(@Param("key")Enum${className} key, @Param("val")Object val);
}
