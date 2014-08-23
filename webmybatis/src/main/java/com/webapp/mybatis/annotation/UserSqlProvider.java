package com.webapp.mybatis.annotation;

import org.apache.ibatis.jdbc.SqlBuilder;

public class UserSqlProvider{
    public String getUser(int id) {
        SqlBuilder.BEGIN();
        SqlBuilder.SELECT("*");
        SqlBuilder.FROM("user");
        SqlBuilder.WHERE("id=#{id}");
        return SqlBuilder.SQL();
    }
}