package com.ok.model;

/**
 * @description: The automatically generated User
 * @table user   
 * @Package  com.ok.model
 * @author   king
 * @Date	 2014-12-06 21:32:38
 * @version  V1.0
 */
public class User {

	/**
     * 描述:自增主键
     * 字段:id  int(10)
     */
	private Integer id;

	/**
     * 描述:用户名
     * 字段:userName  varchar(20)
     */
	private String userName;

	/**
     * 描述:密码
     * 字段:password  varchar(20)
     */
	private String password;

	/**
     * 描述:status
     * 字段:status  int(10)
     */
	private Integer status;

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return this.status;
	}

}

