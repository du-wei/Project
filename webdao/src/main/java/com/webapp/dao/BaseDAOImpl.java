package com.webapp.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Repository;

import com.webapp.util.SpringContextUtil;

/** 统一数据访问接口实现 */

@Repository("baseDao")
public class BaseDAOImpl implements BaseDAO {
    
	private SessionFactory sessionFactory;

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/** 保存指定的持久化对象 */
	public <T> int save(T entity) {
		return Integer.valueOf(this.getSession().save(entity).toString());
	}

	public <T> int save(List<T> entitys) {
		Session session = getSession();
		SessionFactoryImpl impl = (SessionFactoryImpl) SpringContextUtil
				.getBean("sessionFactory");
		int count = impl.getSettings().getJdbcBatchSize();
		for (int i = 0; i < entitys.size(); i++) {
			T t = entitys.get(i);
			session.save(t);
			if (i % count == 0) {
				session.flush();
				session.clear();
			}
		}
		return 1;
	}

	/** 保存或更新指定的持久化对象 */
	public void saveOrUpdate(Object entity) {
		this.getSession().saveOrUpdate(entity);
	}

	/** 删除指定ID的持久化对象 */
	public <T> void delById(Class<T> clazz, Serializable id) {
		this.getSession()
				.createQuery(
						"delete from " + clazz.getName() + " where id = " + id)
				.executeUpdate();
	}

	/** 条件更新数据 */
	public void update(Object object) {
		this.getSession().update(object);
	}

	/** 加载指定ID的持久化对象 */
	@SuppressWarnings("unchecked")
	public <T> T loadById(Class<T> clazz, Serializable id) {
		return (T) this.getSession().get(clazz, id);
	}

	/** 装载指定类的所有持久化对象 */
	public <T> List<T> listAll(String clazz) {
		return this.getSession()
				.createQuery("from " + clazz + " as a order by a.id desc")
				.list();
	}

	public <T> List<T> listAll(T entity) {
		return this
				.getSession()
				.createQuery(
						"from " + entity.getClass().getName()
								+ " as a order by a.id desc").list();
	}

	@Override
	public <T> List<T> listAllByPage(String clazz, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByCriteria(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByCriteria(DetachedCriteria criteria,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String queryString, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> query(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> find(String queryString, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> queryByPage(String hql, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 统计指定类的所有持久化对象 */
	public int countAll(String clazz) {
		return 1;
	}

	/** 统计指定类的查询结果 */
	public int countQuery(String hql) {
		return 1;
	}

	@Override
	public void initialize(Object proxy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	/** 分页装载指定类的所有持久化对象 */
	// @SuppressWarnings("unchecked")
	// public List<T> listAllByPage(String clazz, final int pageNo, final int
	// pageSize) {
	// final String hql = "from "+clazz+ " as a order by a.id desc";
	// List<T> list = this.getSession().executeFind(new HibernateCallback(){
	// public Object doInHibernate(Session session) throws HibernateException{
	// Query query = session.createQuery(hql);
	// query.setMaxResults(pageSize);
	// query.setFirstResult((pageNo-1)*pageSize);
	// List<T> result = query.list();
	// if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
	// return result;
	// }
	// });
	// return list;
	// }

	/** 检索满足标准的数据，返回指定范围的记录 */
	// @SuppressWarnings("unchecked")
	// public List<T> findByCriteria(DetachedCriteria criteria) {
	// return this.getSession().findByCriteria(criteria);
	// }

	/** 检索满足标准的数据，返回指定范围的记录 */
	// @SuppressWarnings("unchecked")
	// public List<T> findByCriteria(DetachedCriteria criteria, int firstResult,
	// int maxResults) {
	// return this.getSession().findByCriteria(criteria, firstResult,
	// maxResults);
	// }

	/** 使用HSQL语句检索数据，返回 Iterator */
	// @SuppressWarnings("unchecked")
	// public Iterator iterate(String queryString) {
	// return this.getSession().iterate(queryString);
	// }

	/** 使用带参数HSQL语句检索数据，返回 Iterator */
	// @SuppressWarnings("unchecked")
	// public Iterator iterate(String queryString, Object[] values) {
	// return this.getSession().iterate(queryString, values);
	// }

	/** 使用HSQL语句检索数据 */
	// @SuppressWarnings("unchecked")
	// public List<T> find(String queryString) {
	// return this.getSession().find(queryString);
	// }

	/** 使用带参数的HSQL语句检索数据 */
	// @SuppressWarnings("unchecked")
	// public List<T> find(String queryString, Object[] values) {
	// return this.getSession().find(queryString, values);
	// }

	/** 查询指定类的满足条件的持久化对象 */
	// @SuppressWarnings("unchecked")
	// public List<T> query(final String hql) {
	// return this.getSession().executeFind(new HibernateCallback(){
	// public Object doInHibernate(Session session) throws HibernateException{
	// return session.createQuery(hql).list();
	// }
	// });
	// }

	/** 分页查询指定类的满足条件的持久化对象 */
	// @SuppressWarnings("unchecked")
	// public List<T> queryByPage(final String hql, final int pageNo, final int
	// pageSize) {
	// return this.getSession().executeFind(new HibernateCallback(){
	// public Object doInHibernate(Session session) throws HibernateException{
	// Query query = session.createQuery(hql);
	// query.setMaxResults(pageSize);
	// query.setFirstResult((pageNo-1)*pageSize);
	// List result = query.list();
	// if (!Hibernate.isInitialized(result))Hibernate.initialize(result);
	// return result;
	// }
	// });
	// }

	/** 强制初始化指定的实体 */
	// public void initialize(Object proxy) {
	// this.getSession().initialize(proxy);
	// }

}