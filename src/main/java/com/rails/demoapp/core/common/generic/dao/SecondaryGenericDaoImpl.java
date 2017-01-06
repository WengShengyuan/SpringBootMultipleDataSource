package com.rails.demoapp.core.common.generic.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.rails.demoapp.core.common.generic.DataSourceConfig;

@Repository("SecondaryGenericDaoImpl")
public class SecondaryGenericDaoImpl<T> extends AbsGenericDaoImpl<T> {

	@Autowired
	@Qualifier(DataSourceConfig.CENTER_2_EMF)
	protected EntityManager sem;

	@Override
	public EntityManager getEntityManager() {
		return sem;
	}
	
}
