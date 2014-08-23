package com.webapp.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.webapp.model.User;
import com.webapp.model.UserMapper;

public class MyBatisTest {
    
    @Test
    public void test() throws Exception {
        SqlSession session = MyBatisBase.createSession();
        try {
            User user = new User();
            user.setPassword("king");
            user.setUsername("king");
            user.setStatus(0);
            session.insert(User.class.getName() + ".add", user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisBase.closeSession(session);
        }
    }

    @Test
    public void testMapper() throws Exception {
        SqlSession session = MyBatisBase.createSession();
        try {
            User user = new User();
            user.setPassword("ssss");

            UserMapper mapper = session.getMapper(UserMapper.class);
            mapper.add(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisBase.closeSession(session);
        }
    }

    
}
