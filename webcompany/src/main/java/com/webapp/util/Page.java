package com.webapp.util;

public class Page {
	
	private int pageSize;  
	private int totalCount; 
	private int totalPage;
	private int beginIndex;
	private int currentPage; 
	private boolean hasPrePage; 
	private boolean hasNextPage; 
	
	public Page(){}
	
	public Page(int pageSize, int totalCount, int totalPage, int beginIndex,int currentPage, boolean hasPrePage, boolean hasNextPage) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.beginIndex = beginIndex;
		this.currentPage = currentPage;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getBeginIndex() {
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isHasPrePage() {
		return hasPrePage;
	}
	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
}
