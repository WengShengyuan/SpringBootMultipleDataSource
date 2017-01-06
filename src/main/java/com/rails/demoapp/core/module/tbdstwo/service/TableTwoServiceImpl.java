package com.rails.demoapp.core.module.tbdstwo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rails.demoapp.core.common.generic.DataSourceConfig;
import com.rails.demoapp.core.module.tbdstwo.dao.TableTwoDao;
import com.rails.demoapp.core.module.tbdstwo.domain.TableTwo;

@Service("TableTwoServiceImpl")
public class TableTwoServiceImpl implements TableTwoService{

	@Resource(name = "TableTwoDaoImpl")
	TableTwoDao tableTwoDao;
	
	@Override
	public TableTwo findById(Object id) throws Exception {
		return tableTwoDao.findById(id);
	}

	@Override
	public List<TableTwo> findAll() throws Exception {
		return tableTwoDao.findAll();
	}

	@Override
	public List<TableTwo> queryByMap(Map<String, Object> map) throws Exception {
		return tableTwoDao.queryByMap(map);
	}

	@Transactional(DataSourceConfig.CENTER_2_TM)
	@Override
	public TableTwo save(TableTwo t) throws Exception {
		return tableTwoDao.save(t);
	}

	@Transactional(DataSourceConfig.CENTER_2_TM)
	@Override
	public TableTwo update(TableTwo t) throws Exception {
		return tableTwoDao.update(t);
	}

	@Transactional(DataSourceConfig.CENTER_2_TM)
	@Override
	public void delete(TableTwo t) throws Exception {
		tableTwoDao.delete(t);
	}

	
	@Override
	public List sqlQuery(String sql) throws Exception {
		return tableTwoDao.sqlQuery(sql);
	}

	@Transactional(DataSourceConfig.CENTER_2_TM)
	@Override
	public int sqlPersist(String sql) throws Exception {
		return tableTwoDao.sqlPersist(sql);
	}

}
