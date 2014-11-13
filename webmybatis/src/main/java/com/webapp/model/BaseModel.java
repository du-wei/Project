package com.webapp.model;

import java.util.Date;

public class BaseModel {

	protected Integer listId;
	protected String title;
	protected String productId;
	protected String source;
	protected String originalUrl;
	protected Integer statusCode;
	protected String statusType;
	protected Date endTime;
	protected Date crawlTime;
	protected Integer crawlType;
	protected Date updateTime;

	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCrawlTime() {
		return crawlTime;
	}
	public void setCrawlTime(Date crawlTime) {
		this.crawlTime = crawlTime;
	}
	public Integer getCrawlType() {
		return crawlType;
	}
	public void setCrawlType(Integer crawlType) {
		this.crawlType = crawlType;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
