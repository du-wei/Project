package com.webapp.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private int count;
	private Set<Revert> revert;
	
	public Message() {
		this.count = 0;
		this.writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="messageId")
	public Set<Revert> getRevert() {
		return revert;
	}
	public void setRevert(Set<Revert> revert) {
		this.revert = revert;
	}
	
}
