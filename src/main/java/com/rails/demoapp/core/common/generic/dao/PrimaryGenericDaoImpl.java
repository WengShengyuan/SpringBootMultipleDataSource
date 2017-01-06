package com.rails.demoapp.core.common.generic.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.rails.demoapp.core.common.generic.DataSourceConfig;

@Repository("PrimaryGenericDaoImpl")
public class PrimaryGenericDaoImpl<T> extends AbsGenericDaoImpl<T>{

	@Autowired
	@Qualifier(DataSourceConfig.CENTER_1_EMF) 
	protected EntityManager em;

	@Override
	EntityManager getEntityManager() {
		return em;
	}

}
