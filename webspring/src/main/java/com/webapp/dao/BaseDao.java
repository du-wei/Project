package com.webapp.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.webapp.database.DataSource;

public interface BaseDao {

	@DataSource("dataSource_new")
    @Select("select * from user_new limit 1")
    public User getUser_new(int id);
	
	@Select("select * from user limit 1")
	public User getUser(int id);


}
