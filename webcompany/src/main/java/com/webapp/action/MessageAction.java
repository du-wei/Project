package com.webapp.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.webapp.entity.Message;
import com.webapp.service.MessageManager;
import com.webapp.util.Page;
import com.webapp.util.PageUtil;

public class MessageAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private int currentPage;
	private Page page;
	private Message message;
	private List<Message> listMsg;
	private MessageManager messageManager;
	public MessageManager getMessageManager() {
		return messageManager;
	}
	@Resource
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}
	public List<Message> getListMsg() {
		return listMsg;
	}
	public void setListMsg(List<Message> listMsg) {
		this.listMsg = listMsg;
	}
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
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public String execute(){
		int totalCount = messageManager.getAllCount();
		page = PageUtil.createPage(10, currentPage, totalCount);
		listMsg = messageManager.showForPage(page.getBeginIndex(), page.getPageSize());
		return SUCCESS;
	}
	public String add(){
		messageManager.add(message);
		this.execute();
		return SUCCESS;
	}
	public String getById(){
		message = messageManager.getById(message.getId());
		return "revert";
	}
	public String updateCount(){
		messageManager.updateCount(message.getId());
		return getById();
	}
}
