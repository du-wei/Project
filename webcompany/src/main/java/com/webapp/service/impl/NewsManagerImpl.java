package com.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.webapp.entity.News;
import com.webapp.service.NewsManager;
@Component("newsManager")
public class NewsManagerImpl implements NewsManager {

//	private NewsDao newsDao;
//	@Resource
//	public void setNewsDao(NewsDao newsDao) {
//		this.newsDao = newsDao;
//	}
	public List<News> getIndexNews() {
//		return newsDao.loadIndexNews();
		return null;
	}
	public News getNewsById(int id) {
//		return newsDao.loadNewsById(id);
		return null;
	}
	public List<News> showForPage(int offset, int length) {
//		return newsDao.showForPage(offset, length);
		return null;
	}
	public void deleteById(int id) {
		
	}
	public void add(News news) {
//		newsDao.save(news);
	}
	public int getAllCount() {
//		return newsDao.getAllCount();
		return 1;
	}
}
