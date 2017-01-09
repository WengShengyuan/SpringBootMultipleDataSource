package com.rails.demoapp.core.common.generic.dao;

import java.util.List;
import java.util.Map;


public interface GenericDao<T> {
	
	public T findById(Object id) throws Exception;
	
	public List<T> findAll() throws Exception;
	public Long countAll() throws Exception;
	public List<T> findAll(int offset, int length) throws Exception;
	
	public List<T> queryByMap(Map<String, Object> map) throws Exception;
	public Long countByMap(Map<String, Object> map) throws Exception;
	public List<T> queryByMap(Map<String, Object> map,int offset, int length) throws Exception;
	
	public T save(T t) throws Exception;
	
	public T update(T t) throws Exception;
	
	public void delete(T t) throws Exception;
	
	public List<T> sqlQuery(String sql) throws Exception;
	public List<T> sqlQuery(String sql, int offset, int length) throws Exception;
	
	public int sqlPersist(String sql) throws Exception;
	
}