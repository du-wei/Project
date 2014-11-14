package com.webapp.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/** 统一数据访问接口实现 */
public class BaseDAOImpl<T> implements BaseDAO<T> {

	// TODO: Found non-transient, non-static member. Please mark as transient or
	// provide accessors.
	private HibernateTemplate hibernateTemplate;

	@Resource
	// 通过@Resource注解注入HibernateTemplate实例
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/** 保存指定的持久化对象 */
	@Override
	public int save(T entity) {
		return (Integer) hibernateTemplate.save(entity);
	}

	/** 保存或更新指定的持久化对象 */
	@Override
	public void saveOrUpdate(T entity) {
		hibernateTemplate.saveOrUpdate(entity);
	}

	/** 删除指定ID的持久化对象 */
	@Override
	public void delById(Class<T> clazz, Serializable id) {
		hibernateTemplate.delete(hibernateTemplate.load(clazz, id));
	}

	/** 条件更新数据 */
	@Override
	public int update(final String hql) {
		return ((Integer) hibernateTemplate.execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
			        throws HibernateException {
				return session.createQuery(hql).executeUpdate();
			}
		})).intValue();
	}

	/** 加载指定ID的持久化对象 */
	@Override
	@SuppressWarnings("unchecked")
	public T loadById(Class<T> clazz, Serializable id) {
		return (T) hibernateTemplate.get(clazz, id);
	}

	/** 加载满足条件的持久化对象 */
	@Override
	@SuppressWarnings("unchecked")
	public T loadObject(final String hql) {
		List<T> list = hibernateTemplate.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
			        throws HibernateException {
				return session.createQuery(hql).list();
			}
		});
		return list.size() > 0 ? list.get(0) : null;
	}

	/** 装载指定类的所有持久化对象 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> listAll(String clazz) {
		return hibernateTemplate.find("from " + clazz
		        + " as a order by a.id desc");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> listAll(T entity) {
		return hibernateTemplate.find("from " + entity.getClass().getName()
		        + " as a order by a.id desc");
	}

	/** 分页装载指定类的所有持久化对象 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> listAllByPage(String clazz, final int pageNo,
	        final int pageSize) {
		final String hql = "from " + clazz + " as a order by a.id desc";
		List<T> list = hibernateTemplate.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
			        throws HibernateException {
				Query query = session.createQuery(hql);
				query.setMaxResults(pageSize);
				query.setFirstResult((pageNo - 1) * pageSize);
				List<T> result = query.list();
				if (!Hibernate.isInitialized(result))
				    Hibernate.initialize(result);
				return result;
			}
		});
		return list;
	}

	/** 检索满足标准的数据，返回指定范围的记录 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		return hibernateTemplate.findByCriteria(criteria);
	}

	/** 检索满足标准的数据，返回指定范围的记录 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
	        int maxResults) {
		return hibernateTemplate.findByCriteria(criteria, firstResult,
		        maxResults);
	}

	/** 使用HSQL语句检索数据，返回 Iterator */
	@Override
	@SuppressWarnings("unchecked")
	public Iterator iterate(String queryString) {
		return hibernateTemplate.iterate(queryString);
	}

	/** 使用带参数HSQL语句检索数据，返回 Iterator */
	@Override
	@SuppressWarnings("unchecked")
	public Iterator iterate(String queryString, Object[] values) {
		return hibernateTemplate.iterate(queryString, values);
	}

	/** 使用HSQL语句检索数据 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString) {
		return hibernateTemplate.find(queryString);
	}

	/** 使用带参数的HSQL语句检索数据 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString, Object[] values) {
		return hibernateTemplate.find(queryString, values);
	}

	/** 查询指定类的满足条件的持久化对象 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> query(final String hql) {
		return hibernateTemplate.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
			        throws HibernateException {
				return session.createQuery(hql).list();
			}
		});
	}

	/** 分页查询指定类的满足条件的持久化对象 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> queryByPage(final String hql, final int pageNo,
	        final int pageSize) {
		return hibernateTemplate.executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
			        throws HibernateException {
				Query query = session.createQuery(hql);
				query.setMaxResults(pageSize);
				query.setFirstResult((pageNo - 1) * pageSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
				    Hibernate.initialize(result);
				return result;
			}
		});
	}

	/** 统计指定类的所有持久化对象 */
	@Override
	public int countAll(String clazz) {
		return (Integer) hibernateTemplate
		        .find("select count(*) from " + clazz).get(0);
	}

	/** 统计指定类的查询结果 */
	@Override
	public int countQuery(String hql) {
		return (Integer) hibernateTemplate.find(hql).get(0);
	}

	/** 强制初始化指定的实体 */
	@Override
	public void initialize(Object proxy) {
		this.hibernateTemplate.initialize(proxy);
	}

	/** 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新） */
	@Override
	public void flush() {
		this.hibernateTemplate.flush();
	}
}
