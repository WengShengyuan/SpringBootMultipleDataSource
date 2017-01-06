package com.rails.demoapp.core.module.tbdstwo.dao;

import org.springframework.stereotype.Repository;

import com.rails.demoapp.core.common.generic.dao.SecondaryGenericDaoImpl;
import com.rails.demoapp.core.module.tbdstwo.domain.TableTwo;

@Repository("TableTwoDaoImpl")
public class TableTwoDaoImpl extends SecondaryGenericDaoImpl<TableTwo> implements TableTwoDao{

}
