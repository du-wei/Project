package com.webapp.mybatis.annotation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.webapp.basetest.TestRunner;
import com.webapp.model.User;

public class MyBatisAnnoTest extends TestRunner {
    
    @Autowired
    BaseDao baseDAO;
    
    @Test
    public void testMybatisAnno1(){
        User user = baseDAO.getUser1(1);
        System.out.println(user.getId() + "-" + user.getUsername());
    }
    
    @Test
    public void testMybatisAnno2(){
        User user = baseDAO.getUser2(1);
        System.out.println(user.getId() + "-" + user.getUsername());
    }
    
}
