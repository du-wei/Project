
package com.webapp.dao;

import com.webapp.model.P2pExt;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webapp.model.Linux;

/**
 * @description: The automatically generated P2pExtDao
 * @Package  com.webapp.dao
 * @Date	 2015-12-11 17:45:21
 * @version  V1.0
 */
public interface P2pExtDao {

	public enum EnumP2pExt{
		list_id,title,product_id,platform,source,original_url,year_rate,min_rate,max_rate,total_money,start_money,join_money,remain_money,progress,repay_limit_time,repay_mode,total_num,crawl_type,platform_type,type,remark1,remark2,remark3,status_code,status_type,start_time,end_time,update_time,crawl_time
	}
	
	String selCols = "list_id listId,title,product_id productId,platform,source,original_url originalUrl,year_rate yearRate,min_rate minRate,max_rate maxRate,total_money totalMoney,start_money startMoney,join_money joinMoney,remain_money remainMoney,progress,repay_limit_time repayLimitTime,repay_mode repayMode,total_num totalNum,crawl_type crawlType,platform_type platformType,type,remark1,remark2,remark3,status_code statusCode,status_type statusType,start_time startTime,end_time endTime,update_time updateTime,crawl_time crawlTime";
	String insCols = "list_id,title,product_id,platform,source,original_url,year_rate,min_rate,max_rate,total_money,start_money,join_money,remain_money,progress,repay_limit_time,repay_mode,total_num,crawl_type,platform_type,type,remark1,remark2,remark3,status_code,status_type,start_time,end_time,update_time,crawl_time";
	String insVals = "#{listId},#{title},#{productId},#{platform},#{source},#{originalUrl},#{yearRate},#{minRate},#{maxRate},#{totalMoney},#{startMoney},#{joinMoney},#{remainMoney},#{progress},#{repayLimitTime},#{repayMode},#{totalNum},#{crawlType},#{platformType},#{type},#{remark1},#{remark2},#{remark3},#{statusCode},#{statusType},#{startTime},#{endTime},#{updateTime},#{crawlTime}";
	String updCols = "title=#{title},product_id=#{productId},platform=#{platform},source=#{source},original_url=#{originalUrl},year_rate=#{yearRate},min_rate=#{minRate},max_rate=#{maxRate},total_money=#{totalMoney},start_money=#{startMoney},join_money=#{joinMoney},remain_money=#{remainMoney},progress=#{progress},repay_limit_time=#{repayLimitTime},repay_mode=#{repayMode},total_num=#{totalNum},crawl_type=#{crawlType},platform_type=#{platformType},type=#{type},remark1=#{remark1},remark2=#{remark2},remark3=#{remark3},status_code=#{statusCode},status_type=#{statusType},start_time=#{startTime},end_time=#{endTime},update_time=#{updateTime},crawl_time=#{crawlTime}";
	String insMulVals = "#{it.listId},#{it.title},#{it.productId},#{it.platform},#{it.source},#{it.originalUrl},#{it.yearRate},#{it.minRate},#{it.maxRate},#{it.totalMoney},#{it.startMoney},#{it.joinMoney},#{it.remainMoney},#{it.progress},#{it.repayLimitTime},#{it.repayMode},#{it.totalNum},#{it.crawlType},#{it.platformType},#{it.type},#{it.remark1},#{it.remark2},#{it.remark3},#{it.statusCode},#{it.statusType},#{it.startTime},#{it.endTime},#{it.updateTime},#{it.crawlTime}";
	
	@Select("select count(1) from p2p_ext")
	public Integer count();
	
	@Select("select " + selCols + " from p2p_ext")
	public List<P2pExt> getAll();
	
	@Select("select " + selCols + " from p2p_ext where list_id=#{listId}")
	public P2pExt getById(Integer listId);
	
	@Select("select " + selCols + " from p2p_ext where #{key}=#{val}")
	public List<P2pExt> getByKV(@Param("key")EnumP2pExt key, @Param("val")Object val);
	
	@Update("update p2p_ext " + updCols + " where list_id=#{listId}")
	public boolean updById(P2pExt p2pExt);
	
	@Insert("insert into p2p_ext (" + insCols + ") values(" + insVals + ")")
    public Integer add(P2pExt p2pExt);
	
	@Insert("<script>insert into p2p_ext (" + insCols + ") values "
            + "<foreach item='it' collection='list' separator=','>(" + insMulVals + ")</foreach></script>")
	public boolean adds(@Param("list")List<P2pExt> list);
	
	@Delete("delete from p2p_ext where list_id=#{listId}")
    public boolean delById(Integer listId);
	
	@Delete("delete from p2p_ext where #{key}=#{val}")
    public boolean delByKV(@Param("key")EnumP2pExt key, @Param("val")Object val);
}
