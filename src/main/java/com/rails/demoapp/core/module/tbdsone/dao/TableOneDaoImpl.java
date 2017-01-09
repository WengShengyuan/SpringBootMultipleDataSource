package com.rails.demoapp.core.module.tbdsone.dao;

import java.util.stream.Stream;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.rails.demoapp.core.common.generic.dao.PrimaryGenericDaoImpl;
import com.rails.demoapp.core.module.tbdsone.domain.TableOne;

@Repository("TableOneDaoImpl")
public class TableOneDaoImpl extends PrimaryGenericDaoImpl<TableOne> implements TableOneDao{

}
