package com.webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.webapp.entity.News;
import com.webapp.service.NewsManager;
import com.webapp.util.Page;
import com.webapp.util.PageUtil;

public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int id;
	private int currentPage = 1;
	private Page page;
	private News news;
	private List<News> listNews;
	private NewsManager newsManager;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public List<News> getListNews() {
		return listNews;
	}
	public void setListNews(List<News> listNews) {
		this.listNews = listNews;
	}
	public NewsManager getNewsManager() {
		return newsManager;
	}
	@Resource
	public void setNewsManager(NewsManager newsManager) {
		this.newsManager = newsManager;
	}
	public String execute(){
		int totalCount = newsManager.getAllCount();
		page = PageUtil.createPage(id, currentPage, totalCount);
		listNews = newsManager.showForPage(page.getBeginIndex(), page.getPageSize());
		return SUCCESS;
	}
	public String getById(){
		news = newsManager.getNewsById(id);
		return "news";
	}
	public String getIndexNews(){
		listNews = newsManager.getIndexNews();
		return SUCCESS;
	}
	public String manager(){
		this.execute();
		return SUCCESS;
	}
	public String add(){
		news.setWriteDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		newsManager.add(news);
		this.execute();
		return SUCCESS;
	}
	public String delete(){
		newsManager.deleteById(news.getId());
		this.execute();
		return SUCCESS;
	}
}
