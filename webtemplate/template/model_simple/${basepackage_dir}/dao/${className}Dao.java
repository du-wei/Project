${gg.setOverride(true)}
<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import ${basepackage}.model.${className};
import org.apache.ibatis.annotations.Select;

/**
 * @description: The automatically generated ${className}Dao
 * @Package  ${basepackage}.dao
 * @author   ${author}
 * @Date	 ${.now?string("yyyy-MM-dd HH:mm:ss")}
 * @version  V1.0
 */
public interface ${className}Dao {

	String selCols = "<#list table.columns as column>${column.columnNameLower}<#if column.columnNameLower != column.sqlName> ${column.sqlName}</#if><#if column_has_next>,</#if></#list>";
	String insCols = "<#list table.columns as column>${column.sqlName}<#if column_has_next>,</#if></#list>";
	String insColVals= "<#list table.columns as column>${'#{'}${column.columnNameLower}${'}'}<#if column_has_next>,</#if></#list>";

	@Select("select " + selCols + " from ${table.sqlName} where id = <@tmpExp 'id'/>")
	public ${className} getById(Integer id);

}
