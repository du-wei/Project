package com.webapp.mybatis.annotation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.webapp.basetest.BaseRunner;
import com.webapp.model.User;
import com.webapp.mybatis.annotation.dao.BaseDao;
import com.webapp.mybatis.annotation.dao.CompanyDao;

public class BaseDaoTest extends BaseRunner {
    
	@Autowired
	private BaseDao baseDAO;
	
	@Autowired
	private CompanyDao companyDao;
	
    @Test
    public void testBaseDao(){
        User user = baseDAO.getUser1(1);
        System.out.println(baseDAO.getUser());
        System.out.println(user.getId() + "-" + user.getUsername());
    }
    
	@Test
	public void testCompany() {
//
		companyDao.getPPP(129725);
//		System.out.println(p2pModel.getTitle());
	}
    
}
