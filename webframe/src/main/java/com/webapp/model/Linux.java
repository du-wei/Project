package com.webapp.model;

import java.util.Date;

/**
 * @description: The automatically generated Linux
 * @table linux   
 * @Package  com.webapp.model
 * @Date	 2015-10-19 23:32:00
 * @version  V1.0
 */
public class Linux {

	/**
     * 描述:id
     * 字段:id  int(10)
     */
	private Integer id;

	/**
     * 描述:命令参数全称
     * 字段:name  varchar(255)
     */
	private String name;

	/**
     * 描述:命令类型
     * 字段:type  varchar(20)
     */
	private String type;

	/**
     * 描述:tsort
     * 字段:tsort  int(10)
     */
	private Integer tsort;

	/**
     * 描述:命令参数key
     * 字段:ckey  varchar(20)
     */
	private String ckey;

	/**
     * 描述:ksort
     * 字段:ksort  int(10)
     */
	private Integer ksort;

	/**
     * 描述:filter
     * 字段:filter  varchar(20)
     */
	private String filter;

	/**
     * 描述:命令语句
     * 字段:cmd  varchar(255)
     */
	private String cmd;

	/**
     * 描述:命令描述
     * 字段:desc_en  varchar(255)
     */
	private String descEn;

	/**
     * 描述:descZn
     * 字段:desc_zn  varchar(255)
     */
	private String descZn;

	/**
     * 描述:命令状态 1 可用 0 禁用 -1帮助
     * 字段:status  tinyint(3)
     */
	private Integer status;

	/**
     * 描述:insertTime
     * 字段:insert_time  datetime(19)
     */
	private Date insertTime;
	//【非数据库字段，查询时使用】
	private Date insertTimeBegin;
	//【非数据库字段，查询时使用】
	private Date insertTimeEnd;

	/**
     * 描述:updateTime
     * 字段:update_time  timestamp(19)
     */
	private Date updateTime;
	//【非数据库字段，查询时使用】
	private Date updateTimeBegin;
	//【非数据库字段，查询时使用】
	private Date updateTimeEnd;

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}

	public void setTsort(Integer tsort) {
		this.tsort = tsort;
	}
	public Integer getTsort() {
		return this.tsort;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}
	public String getCkey() {
		return this.ckey;
	}

	public void setKsort(Integer ksort) {
		this.ksort = ksort;
	}
	public Integer getKsort() {
		return this.ksort;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getFilter() {
		return this.filter;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getCmd() {
		return this.cmd;
	}

	public void setDescEn(String descEn) {
		this.descEn = descEn;
	}
	public String getDescEn() {
		return this.descEn;
	}

	public void setDescZn(String descZn) {
		this.descZn = descZn;
	}
	public String getDescZn() {
		return this.descZn;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return this.status;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getInsertTime() {
		return this.insertTime;
	}

    public void setInsertTimeBegin(Date insertTimeBegin) {
		this.insertTimeBegin = insertTimeBegin;
	}
	public Date getInsertTimeBegin() {
		return this.insertTimeBegin;
	}
	public void setInsertTimeEnd(Date insertTimeEnd) {
		this.insertTimeEnd = insertTimeEnd;
	}
	public Date getInsertTimeEnd() {
		return this.insertTimeEnd;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}

    public void setUpdateTimeBegin(Date updateTimeBegin) {
		this.updateTimeBegin = updateTimeBegin;
	}
	public Date getUpdateTimeBegin() {
		return this.updateTimeBegin;
	}
	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}
	public Date getUpdateTimeEnd() {
		return this.updateTimeEnd;
	}
}

