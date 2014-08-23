package com.webapp.util;

public class PageUtil {
	
	public static Page createPage(Page page, int totalCount){
		int pageSize = getPageSize(page.getPageSize());
		int totalPage = getTotalPage(page.getPageSize(), totalCount);
		int offset = getOffset(page.getPageSize(), page.getCurrentPage());
		int curPage = getCurrentPage(page.getCurrentPage());
		boolean hasPrePage = hasPrePage(page.getCurrentPage());
		boolean hasNextPage = hasNextPage(page.getCurrentPage(), totalPage);
		return new Page(pageSize, totalCount, totalPage, offset, curPage, hasPrePage, hasNextPage);
	}
	public static Page createPage(int pageSize, int currentPage, int totalCount){
		pageSize = getPageSize(pageSize);
		int totalPage = getTotalPage(pageSize, totalCount);
		int offset = getOffset(pageSize, currentPage);
		int curPage = getCurrentPage(currentPage);
		boolean hasPrePage = hasPrePage(currentPage);
		boolean hasNextPage = hasNextPage(currentPage, totalPage);
		return new Page(pageSize, totalCount, totalPage, offset, curPage, hasPrePage, hasNextPage);
	}
	
	public static int getPageSize(int pageSize){
		return pageSize == 0 ? 10 : pageSize;
	}
	public static int getTotalPage(int pageSize,int totalCount){  
		return totalCount%pageSize == 0 ? totalCount/pageSize : totalCount/pageSize+1;
	}
	public static int getOffset(int pageSize,int currentPage){
		return pageSize * (currentPage - 1) + 1;
	}
	public static int getCurrentPage(int currentPage){
		return currentPage == 0 ? 1:currentPage;
	}
	public static boolean hasPrePage(int currentPage){
		return currentPage != 1 ? true : false;
	}
	public static boolean hasNextPage(int currentPage, int totalPage){
		return currentPage != totalPage ? true : false;
	}
}
