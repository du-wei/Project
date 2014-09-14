package com.webapp.mybatis.annotation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.webapp.model.User;

public interface BaseDao {

    @Select("select * from user where id=:id limit 1")
    public User getUser1(int id);

    @Select("select * from user")
    public List<Map<String, String>> getUser();

    @SelectProvider(type=UserSqlProvider.class, method="getUser")
    public User getUser2(int id);



}
