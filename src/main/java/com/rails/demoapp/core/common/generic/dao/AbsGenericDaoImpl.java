package com.rails.demoapp.core.common.generic.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public abstract class AbsGenericDaoImpl<T>  implements GenericDao<T>{

	@SuppressWarnings("unused")
	private Class<T> persistentClass;
	
	@Override
	public T findById(Object id) throws Exception {
		return (T) getEntityManager().find(this.getPersistentClass(), id);
	}
	
	@Override
	public List<T> findAll() throws Exception{
		List<T> resultList = null;
		String sql = "from " + getPersistentClass().getName() + " obj ";
		TypedQuery<T> query = getEntityManager().createQuery(sql, this.getPersistentClass());
		resultList = query.getResultList();
		return resultList;
	}
	@Override
	public Long countAll() throws Exception {
		String sql = "select count(*) from " + getPersistentClass().getName() + " obj ";
		Query query = getEntityManager().createQuery(sql);
		return  (Long) query.getResultList().get(0);
	}
	@Override
	public List<T> findAll(int offset, int length) throws Exception{
		List<T> resultList = null;
		String sql = "from " + getPersistentClass().getName() + " obj ";
		TypedQuery<T> query = getEntityManager().createQuery(sql, this.getPersistentClass());
		resultList = query.setFirstResult(offset).setMaxResults(length).getResultList();
		return resultList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryByMap(Map<String, Object> map) throws Exception{
		List<T> resultList = null;
		StringBuilder sql = new StringBuilder("SELECT o from " + getPersistentClass().getName() + " o WHERE ");
		StringBuilder conStr = new StringBuilder("");
		// 拼接条件
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (value instanceof List) {
				conStr = conStr.append(String.format(" o.%s in (:%s) and", key, key));
			} else {
				conStr = conStr.append(String.format(" o.%s = :%s and", key, key));
			}
		}
		// 去掉最后一个and
		sql.append(conStr.toString().substring(0, conStr.length() - 4));
		Query query = getEntityManager().createQuery(sql.toString());
		for (String key : map.keySet()) {
			query = query.setParameter(key, map.get(key));
		}
		resultList =  query.getResultList();
		return resultList;
	}
	
	@Override
	public Long countByMap(Map<String, Object> map) throws Exception{
		StringBuilder sql = new StringBuilder("SELECT count(*) from " + getPersistentClass().getName() + " o WHERE ");
		StringBuilder conStr = new StringBuilder("");
		// 拼接条件
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (value instanceof List) {
				conStr = conStr.append(String.format(" o.%s in (:%s) and", key, key));
			} else {
				conStr = conStr.append(String.format(" o.%s = :%s and", key, key));
			}
		}
		// 去掉最后一个and
		sql.append(conStr.substring(0, conStr.length() - 4));
		Query query = getEntityManager().createQuery(sql.toString());
		for (String key : map.keySet()) {
			query = query.setParameter(key, map.get(key));
		}
		return (Long) query.getResultList().get(0);
	}
	
	@Override
	public List<T> queryByMap(Map<String, Object> map,int offset, int length) throws Exception{
		List<T> resultList = null;
		StringBuilder sql = new StringBuilder("SELECT o from " + getPersistentClass().getName() + " o WHERE ");
		StringBuilder conStr = new StringBuilder("");
		// 拼接条件
		for (String key : map.keySet()) {
			Object value = map.get(key);
			if (value instanceof List) {
				conStr = conStr.append(String.format(" o.%s in (:%s) and", key, key));
			} else {
				conStr = conStr.append(String.format(" o.%s = :%s and", key, key));
			}
		}
		// 去掉最后一个and
		sql.append(conStr.toString().substring(0, conStr.length() - 4));
		Query query = getEntityManager().createQuery(sql.toString());
		for (String key : map.keySet()) {
			query = query.setParameter(key, map.get(key));
		}
		resultList =  query.setFirstResult(offset).setMaxResults(length).getResultList();
		return resultList;
	}
	
	@Override
	public T save(T t) throws Exception{
		getEntityManager().persist(t);
		return (T) t;
	}
	
	@Override
	public T update(T t) throws Exception{
		Object object = getEntityManager().merge(t);
		return (T) object;
	}
	
	@Override
	public void delete(T t) throws Exception{
		Object object = getEntityManager().merge(t);
		getEntityManager().remove(object);
	}

	@Override
	public List<T> sqlQuery(String sql) throws Exception{
		Query query =  getEntityManager().createNativeQuery(sql);
		List results = query.getResultList();
		return results;
	}
	@Override
	public List<T> sqlQuery(String sql, int offset, int length) throws Exception {
		Query query =  getEntityManager().createNativeQuery(sql);
		List results = query.setFirstResult(offset).setMaxResults(length).getResultList();
		return results;
	}
	
	@Override
	public int sqlPersist(String sql) throws Exception{
		Query query =  getEntityManager().createNativeQuery(sql);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getPersistentClass() {
		return this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	abstract EntityManager getEntityManager();
}
