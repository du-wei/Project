package com.webapp.service;

import java.util.List;

import com.webapp.entity.User;

public interface UserManager {

	public boolean checkExistsByName(String userName);

	public boolean validate(String userName, String password);

	public void save(User user);

	public List<User> loadAll();

	public void delectById(int id);
	
	public List<User> getForPage(final int offset, final int length);
	public int getAllCount();

}