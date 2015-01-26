
package com.ok.dao;

import org.apache.ibatis.annotations.Select;

import com.ok.model.User;

/**
 * @description: The automatically generated UserDao
 * @Package  com.ok.dao
 * @author   king
 * @Date	 2014-12-07 20:47:37
 * @version  V1.0
 */
public interface UserDao {

	String selCols = "id,userName user_name,password,status";
	String insCols = "id,user_name,password,status";
	String insColVals= "#{id},#{userName},#{password},#{status}";

	@Select("select " + selCols + " from user where id = #{id}")
	public User getById(Integer id);

}
