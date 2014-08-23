package com.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.webapp.entity.User;
import com.webapp.service.UserManager;
@Component("userManager")
public class UserManagerImpl implements UserManager{
	
//	private UserDao userDao;
//	@Resource
//	public void setUserDao(UserDao userDao) {
//		this.userDao = userDao;
//	}
	public boolean checkExistsByName(String userName) {
		return false;
	}
	public boolean validate(String userName, String password) {
//		return userDao.validate(userName, password);
		return false;
	}
	public void save(User user) {
//		userDao.save(user);
	}
	public List<User> loadAll() {
		return null;
	}
	public void delectById(int id) {
//		userDao.delectById(id);
	}
	public List<User> getForPage(int offset, int length) {
//		return userDao.getForPage(offset, length);
		return null;
	}
	public int getAllCount() {
//		return userDao.getAllCount();
		return 1;
	}
}
