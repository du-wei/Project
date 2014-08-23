package com.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.webapp.entity.User;
import com.webapp.service.UserManager;
import com.webapp.util.Page;
import com.webapp.util.PageUtil;

public class UserAction extends ActionSupport implements SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;
	
	private int currentPage;
	private Page page;
	private User user;
	private List<User> listUser;
	private UserManager userManager;
	private Map<String, Object> session;
	private Map<String, Object> application;

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getListUser() {
		return listUser;
	}
	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}
	public UserManager getUserManager() {
		return userManager;
	}
	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public Map<String, Object> getApplication() {
		return application;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	@SuppressWarnings("unchecked")
	public String execute(){
		boolean validate = userManager.validate(user.getUserName(), user.getPassword());
		if(validate){
			listUser = (List<User>) application.get("users");
			if(listUser == null || listUser.size() == 0){
				listUser = new ArrayList<User>();
				listUser.add(user);
			}else{
				boolean bool = true;
				for (int i = 0; i < listUser.size(); i++) {
					if (user.getUserName().equals(listUser.get(i).getUserName())) {
						bool = false;
						break;
					}
				}
				if(bool) listUser.add(user);
			}
			session.put("user", user);
			application.put("users", listUser);
			return SUCCESS;
		}
		return "userLogin";
	}
	public String valiAdmin(){
		boolean validate = userManager.validate(user.getUserName(), user.getPassword());
		System.out.println(user.getUserName());
		System.out.println(user.getStatus());
		System.out.println(validate);
		if(validate){
			session.put("admin", user);
			return "admin";
		}
		return "adminLogin";
	}
	public String manager(){
		page = PageUtil.createPage(10, currentPage, userManager.getAllCount());
		listUser = userManager.getForPage(page.getBeginIndex(), page.getPageSize());
		return SUCCESS;
	}
	public String add(){
		userManager.save(user);
		this.manager();
		return SUCCESS;
	}
	public String delete(){
		userManager.delectById(user.getId());
		this.manager();
		return SUCCESS;
	}
	public String exitAdmin(){
		session.remove("admin");
		return "exitAdmin";
	}
}
