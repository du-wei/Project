package com.webapp.model;

import java.util.Date;

/**
 * @description: The automatically generated P2pExt
 * @table p2p_ext   
 * @Package  com.webapp.model
 * @Date	 2015-12-04 12:00:55
 * @version  V1.0
 */
public class P2pExt {

	/**
     * 描述:listId
     * 字段:list_id  int(10)
     */
	private Integer listId;

	/**
     * 描述:标题
     * 字段:title  varchar(255)
     */
	private String title;

	/**
     * 描述:productId
     * 字段:product_id  varchar(255)
     */
	private String productId;

	/**
     * 描述:平台名称
     * 字段:platform  varchar(100)
     */
	private String platform;

	/**
     * 描述:平台站点地址
     * 字段:source  varchar(255)
     */
	private String source;

	/**
     * 描述:标的url地址
     * 字段:original_url  varchar(255)
     */
	private String originalUrl;

	/**
     * 描述:预期年化收益率
     * 字段:year_rate  double(10)
     */
	private Double yearRate;

	/**
     * 描述:预期最低年化收益率
     * 字段:min_rate  double(10)
     */
	private Double minRate;

	/**
     * 描述:预期最大年化收益率
     * 字段:max_rate  double(10)
     */
	private Double maxRate;

	/**
     * 描述:totalMoney
     * 字段:total_money  double(15)
     */
	private Double totalMoney;

	/**
     * 描述:起投金额
     * 字段:start_money  double(15)
     */
	private Double startMoney;

	/**
     * 描述:已投金额
     * 字段:join_money  double(15)
     */
	private Double joinMoney;

	/**
     * 描述:剩余金额
     * 字段:remain_money  double(15)
     */
	private Double remainMoney;

	/**
     * 描述:进度，100%代表标已结束，还款中
     * 字段:progress  double(10)
     */
	private Double progress;

	/**
     * 描述:投资期限 单位月
     * 字段:repay_limit_time  double(10)
     */
	private Double repayLimitTime;

	/**
     * 描述:还款方式
     * 字段:repay_mode  varchar(255)
     */
	private String repayMode;

	/**
     * 描述:投标人数
     * 字段:total_num  int(10)
     */
	private Integer totalNum;

	/**
     * 描述:抓取类型 0全量 1批量
     * 字段:crawl_type  int(10)
     */
	private Integer crawlType;

	/**
     * 描述:平台模块类型
     * 字段:platform_type  varchar(100)
     */
	private String platformType;

	/**
     * 描述:标的类型,各个平台可能不一样
     * 字段:type  varchar(100)
     */
	private String type;

	/**
     * 描述:备注1
     * 字段:remark1  varchar(400)
     */
	private String remark1;

	/**
     * 描述:备注2
     * 字段:remark2  varchar(400)
     */
	private String remark2;

	/**
     * 描述:备注3
     * 字段:remark3  varchar(400)
     */
	private String remark3;

	/**
     * 描述:0可投资 1已满标 2还款中 3已撤标 4三天未抓取
     * 字段:status_code  int(10)
     */
	private Integer statusCode;

	/**
     * 描述:statusType
     * 字段:status_type  varchar(20)
     */
	private String statusType;

	/**
     * 描述:startTime
     * 字段:start_time  datetime(19)
     */
	private Date startTime;
	//【非数据库字段，查询时使用】
	private Date startTimeBegin;
	//【非数据库字段，查询时使用】
	private Date startTimeEnd;

	/**
     * 描述:endTime
     * 字段:end_time  datetime(19)
     */
	private Date endTime;
	//【非数据库字段，查询时使用】
	private Date endTimeBegin;
	//【非数据库字段，查询时使用】
	private Date endTimeEnd;

	/**
     * 描述:updateTime
     * 字段:update_time  timestamp(19)
     */
	private Date updateTime;
	//【非数据库字段，查询时使用】
	private Date updateTimeBegin;
	//【非数据库字段，查询时使用】
	private Date updateTimeEnd;

	/**
     * 描述:抓取时间
     * 字段:crawl_time  datetime(19)
     */
	private Date crawlTime;
	//【非数据库字段，查询时使用】
	private Date crawlTimeBegin;
	//【非数据库字段，查询时使用】
	private Date crawlTimeEnd;

	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public Integer getListId() {
		return this.listId;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductId() {
		return this.productId;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getPlatform() {
		return this.platform;
	}

	public void setSource(String source) {
		this.source = source;
	}
	public String getSource() {
		return this.source;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getOriginalUrl() {
		return this.originalUrl;
	}

	public void setYearRate(Double yearRate) {
		this.yearRate = yearRate;
	}
	public Double getYearRate() {
		return this.yearRate;
	}

	public void setMinRate(Double minRate) {
		this.minRate = minRate;
	}
	public Double getMinRate() {
		return this.minRate;
	}

	public void setMaxRate(Double maxRate) {
		this.maxRate = maxRate;
	}
	public Double getMaxRate() {
		return this.maxRate;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setStartMoney(Double startMoney) {
		this.startMoney = startMoney;
	}
	public Double getStartMoney() {
		return this.startMoney;
	}

	public void setJoinMoney(Double joinMoney) {
		this.joinMoney = joinMoney;
	}
	public Double getJoinMoney() {
		return this.joinMoney;
	}

	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}
	public Double getRemainMoney() {
		return this.remainMoney;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}
	public Double getProgress() {
		return this.progress;
	}

	public void setRepayLimitTime(Double repayLimitTime) {
		this.repayLimitTime = repayLimitTime;
	}
	public Double getRepayLimitTime() {
		return this.repayLimitTime;
	}

	public void setRepayMode(String repayMode) {
		this.repayMode = repayMode;
	}
	public String getRepayMode() {
		return this.repayMode;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setCrawlType(Integer crawlType) {
		this.crawlType = crawlType;
	}
	public Integer getCrawlType() {
		return this.crawlType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	public String getPlatformType() {
		return this.platformType;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public String getRemark3() {
		return this.remark3;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public Integer getStatusCode() {
		return this.statusCode;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String getStatusType() {
		return this.statusType;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStartTime() {
		return this.startTime;
	}

    public void setStartTimeBegin(Date startTimeBegin) {
		this.startTimeBegin = startTimeBegin;
	}
	public Date getStartTimeBegin() {
		return this.startTimeBegin;
	}
	public void setStartTimeEnd(Date startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}
	public Date getStartTimeEnd() {
		return this.startTimeEnd;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndTime() {
		return this.endTime;
	}

    public void setEndTimeBegin(Date endTimeBegin) {
		this.endTimeBegin = endTimeBegin;
	}
	public Date getEndTimeBegin() {
		return this.endTimeBegin;
	}
	public void setEndTimeEnd(Date endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}
	public Date getEndTimeEnd() {
		return this.endTimeEnd;
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
	public void setCrawlTime(Date crawlTime) {
		this.crawlTime = crawlTime;
	}
	public Date getCrawlTime() {
		return this.crawlTime;
	}

    public void setCrawlTimeBegin(Date crawlTimeBegin) {
		this.crawlTimeBegin = crawlTimeBegin;
	}
	public Date getCrawlTimeBegin() {
		return this.crawlTimeBegin;
	}
	public void setCrawlTimeEnd(Date crawlTimeEnd) {
		this.crawlTimeEnd = crawlTimeEnd;
	}
	public Date getCrawlTimeEnd() {
		return this.crawlTimeEnd;
	}
}

