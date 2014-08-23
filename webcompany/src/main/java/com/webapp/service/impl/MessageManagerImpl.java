package com.webapp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.webapp.dao.BaseDAO;
import com.webapp.entity.Message;
import com.webapp.service.MessageManager;
@Component("messageManager")
public class MessageManagerImpl implements MessageManager {

	private BaseDAO baseDAO;
	@Resource
	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	public int getAllCount() {
//		return messageDao.loadAllCount();
		return 1;
	}
	public List<Message> showForPage(int offset, int length) {
//		return messageDao.showForPage(offset, length);
		return null;
	}
	public int add(Message message) {
		return baseDAO.save(message);
	}
	public Message getById(int id) {
		return baseDAO.loadById(Message.class, id);
	}
	public void updateCount(int id) {
	}

}
