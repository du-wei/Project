package com.webapp.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/** 统一数据访问接口 */
public interface BaseDAO {

	/** 保存指定的持久化对象 */
	<T> int save(T entity);

	<T> int save(List<T> entitys);

	/** 保存或更新指定的持久化对象 */
	void saveOrUpdate(Object entity);

	/** 删除指定ID的持久化对象 */
	<T> void delById(Class<T> clazz, Serializable id);

	/** 条件更新数据 */
	<T> void update(Object object);

	/** 加载指定ID的持久化对象 */
	<T> T loadById(Class<T> clazz, Serializable id);

	/** 装载指定类的所有持久化对象 */
	<T> List<T> listAll(String clazz);

	/** 装载指定类的所有持久化对象 */
	<T> List<T> listAll(T entity);

	/** 分页装载指定类的所有持久化对象 */
	<T> List<T> listAllByPage(String clazz, int pageNo, int pageSize);

	/** 使用指定的检索标准检索数据，返回部分记录 */
	<T> List<T> findByCriteria(DetachedCriteria criteria);

	/** 使用指定的检索标准检索数据，返回部分记录 */
	<T> List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
			int maxResults);

	/** 使用HSQL语句检索数据，返回 Iterator */
	@SuppressWarnings("unchecked")
	public Iterator iterate(String queryString);

	/** 使用带参数HSQL语句检索数据，返回 Iterator */
	@SuppressWarnings("unchecked")
	public Iterator iterate(String queryString, Object[] values);

	/** 查询指定类的满足条件的持久化对象 */
	<T> List<T> query(String hql);

	/** 使用HSQL语句检索数据 */
	<T> List<T> find(String queryString);

	/** 使用带参数的HSQL语句检索数据 */
	<T> List<T> find(String queryString, Object[] values);

	/** 分页查询指定类的满足条件的持久化对象 */
	<T> List<T> queryByPage(String hql, int pageNo, int pageSize);

	/** 统计指定类的所有持久化对象 */
	int countAll(String clazz);

	/** 统计指定类的查询结果 */
	int countQuery(String hql);

	/** 强制初始化指定的实体 */
	void initialize(Object proxy);

	/** 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新） */
	void flush();

}
