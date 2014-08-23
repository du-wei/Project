package com.webapp.service;

import java.util.List;

import com.webapp.entity.Message;

public interface MessageManager {
	public int getAllCount();
	public List<Message> showForPage(final int offset, final int length);
	public int add(Message message);
	public Message getById(int id);
	public void updateCount(int id);
}
