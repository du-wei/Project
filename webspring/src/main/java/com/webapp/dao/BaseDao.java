package com.webapp.dao;

import org.apache.ibatis.annotations.Select;

import com.webapp.utils.datasource.DataSource;

public interface BaseDao {

	@DataSource("datasource")
    @Select("select * from user_new limit 1")
    public User getUser_new(int id);

	@Select("select * from user limit 1")
	public User getUser(int id);


}
