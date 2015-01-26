package com.webapp.mybatis.annotation.dao;

import org.apache.ibatis.annotations.Select;

public interface CompanyDao {

	@Select("select count(*) from p2p limit 1")
    public int getPPP(int id);
	
}
