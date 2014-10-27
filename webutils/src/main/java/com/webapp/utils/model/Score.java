package com.webapp.utils.model;

public class Score {

	private int id;
	private String stuId;
	private String courseId;
	private double score;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}


}
