package com.webapp.service;

import java.util.List;

import com.webapp.entity.News;

public interface NewsManager {
	public List<News> getIndexNews();
	public News getNewsById(int id);
	public List<News> showForPage(final int offset, final int length);
	public int getAllCount();
	public void deleteById(int id);
	public void add(News news);
}
