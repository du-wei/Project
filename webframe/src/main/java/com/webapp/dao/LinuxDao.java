
package com.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.webapp.model.Linux;

/**
 * @description: The automatically generated LinuxDao
 * @Package  com.dao
 * @Date	 2015-10-16 13:53:58
 * @version  V1.0
 */
public interface LinuxDao {

	String selCols = "id,name,type,tsort,ckey,ksort,filter,cmd,desc_en descEn,desc_zn descZn,status,insert_time insertTime,update_time updateTime";

	@Select("select count(1) from linux")
	public Integer count();

	@Select("select " + selCols + " from linux where status = 1")
	public List<Linux> getAll();

	@Select("select " + selCols + " from linux where status = -1")
	public List<Linux> getHelp();

	@Delete("delete from linux where id=#{id}")
    public boolean delById(Integer id);

}
