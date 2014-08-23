package com.webapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class News implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String content;
	private String writeDate;
	
	public News() {}
	public News(int id, String title, String content, String writeDate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.writeDate = writeDate;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
}
