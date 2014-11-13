package com.webapp.model;

import java.util.Date;

public class P2pModel extends BaseModel {

	private Integer type;
    private String platform;
    private Integer platformLevel;
    private String agency;
    private Double dayRate;
    private Double yearRate;
    private Double totalMoney;
    private Double startMoney;
    private Double remainMoney;
    private Double progress;
    private Double repayLimitTime;
    private String repayMode;
    private Integer totalNum;
    private String detailDesc;
    private Date startTime;


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getPlatformLevel() {
		return platformLevel;
	}

	public void setPlatformLevel(Integer platformLevel) {
		this.platformLevel = platformLevel;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public Double getDayRate() {
		return dayRate;
	}

	public void setDayRate(Double dayRate) {
		this.dayRate = dayRate;
	}

	public Double getYearRate() {
		return yearRate;
	}

	public void setYearRate(Double yearRate) {
		this.yearRate = yearRate;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(Double startMoney) {
		this.startMoney = startMoney;
	}

	public Double getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}

	public Double getRepayLimitTime() {
		return repayLimitTime;
	}

	public void setRepayLimitTime(Double repayLimitTime) {
		this.repayLimitTime = repayLimitTime;
	}

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public String getRepayMode() {
		return repayMode;
	}

	public void setRepayMode(String repayMode) {
		this.repayMode = repayMode;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getDetailDesc() {
		return detailDesc;
	}

	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}



}
