package com.rails.demoapp.core.module.tbdsboth.dao;

import org.springframework.stereotype.Repository;

import com.rails.demoapp.core.common.generic.dao.SecondaryGenericDaoImpl;
import com.rails.demoapp.core.module.tbdsboth.domain.TableThree;

@Repository("SecondaryTableThreeDaoImpl")
public class SecondaryTableThreeDaoImpl  extends SecondaryGenericDaoImpl<TableThree> implements TableThreeDao{

}
