package com.webapp.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.webapp.entity.Revert;
import com.webapp.service.RevertManager;

public class RevertAction extends ActionSupport {
	
	
	private static final long serialVersionUID = 1L;
	private Revert revert;
	private RevertManager revertManager;
	
	public Revert getRevert() {
		return revert;
	}
	public void setRevert(Revert revert) {
		this.revert = revert;
	}
	public RevertManager getRevertManager() {
		return revertManager;
	}
	@Resource
	public void setRevertManager(RevertManager revertManager) {
		this.revertManager = revertManager;
	}
	public String execute(){
		revertManager.add(revert);
		return SUCCESS;
	}
}
