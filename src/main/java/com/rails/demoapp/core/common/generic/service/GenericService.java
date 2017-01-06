package com.rails.demoapp.core.common.generic.service;

import java.util.List;
import java.util.Map;

public interface GenericService<T> {

	public T findById(Object id) throws Exception;
	
	public List<T> findAll() throws Exception;
	
	public List<T> queryByMap(Map<String, Object> map) throws Exception;
	
	public T save(T t) throws Exception;
	
	public T update(T t) throws Exception;
	
	public void delete(T t) throws Exception;
	
	public List sqlQuery(String sql) throws Exception;
	
	public int sqlPersist(String sql) throws Exception;
}
