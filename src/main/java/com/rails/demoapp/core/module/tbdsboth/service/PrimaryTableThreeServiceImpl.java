package com.rails.demoapp.core.module.tbdsboth.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rails.demoapp.core.common.generic.DataSourceConfig;
import com.rails.demoapp.core.module.tbdsboth.dao.TableThreeDao;
import com.rails.demoapp.core.module.tbdsboth.domain.TableThree;

@Service("PrimaryTableThreeServiceImpl")
public class PrimaryTableThreeServiceImpl implements TableThreeService{

	@Resource(name = "PrimaryTableThreeDaoImpl")
	private TableThreeDao tableThreeDao;
	
	@Override
	public TableThree findById(Object id) throws Exception {
		return tableThreeDao.findById(id);
	}

	@Override
	public List<TableThree> findAll() throws Exception {
		return tableThreeDao.findAll();
	}

	@Override
	public List<TableThree> queryByMap(Map<String, Object> map) throws Exception {
		return tableThreeDao.queryByMap(map);
	}

	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public TableThree save(TableThree t) throws Exception {
		return tableThreeDao.save(t);
	}

	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public TableThree update(TableThree t) throws Exception {
		return tableThreeDao.update(t);
	}

	
	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public void delete(TableThree t) throws Exception {
		tableThreeDao.delete(t);
	}


	@Override
	public List sqlQuery(String sql) throws Exception {
		return tableThreeDao.sqlQuery(sql);
	}

	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public int sqlPersist(String sql) throws Exception {
		return tableThreeDao.sqlPersist(sql);
	}

}
