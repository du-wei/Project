package com.webapp.mybatis.annotation;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.webapp.model.User;

public class BaseDaoTest {
    
    private static ApplicationContext context;
    
    @BeforeClass
    public static void before(){
        context = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        
    }
    
    @Test
    public void testBaseDao(){
        BaseDao baseDAO = (BaseDao) context.getBean(StringUtils.uncapitalize(BaseDao.class.getSimpleName()));
//        User user = baseDAO.getUser1(1);
        
        User user = baseDAO.getUser1(1);
        System.out.println(baseDAO.getUser());
        System.out.println(user.getId() + "-" + user.getUsername());
    }
    
}
