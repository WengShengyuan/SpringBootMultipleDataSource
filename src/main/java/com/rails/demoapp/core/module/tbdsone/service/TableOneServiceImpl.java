package com.rails.demoapp.core.module.tbdsone.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rails.demoapp.core.common.generic.DataSourceConfig;
import com.rails.demoapp.core.common.generic.cache.CacheService;
import com.rails.demoapp.core.module.tbdsone.dao.TableOneDao;
import com.rails.demoapp.core.module.tbdsone.domain.TableOne;
import com.rails.demoapp.core.module.tbdstwo.dao.TableTwoDao;
import com.rails.demoapp.core.module.tbdstwo.dao.TableTwoDaoImpl;

@Service("TableOneServiceImpl")
public class TableOneServiceImpl implements TableOneService{

	@Resource(name = "TableOneDaoImpl")
	private TableOneDao tableOneDao;
	
	@Resource(name = "TableTwoDaoImpl")
	private TableTwoDao tableTwoDao;
	
	
	@Override
	public TableOne findById(Object id) throws Exception {
		
		return tableOneDao.findById(id);
	}

	@Override
	public List<TableOne> findAll() throws Exception {
		return tableOneDao.findAll();
	}

	@Override
	public List<TableOne> queryByMap(Map<String, Object> map) throws Exception {
		return tableOneDao.queryByMap(map);
	}

	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public TableOne save(TableOne t) throws Exception {
		return tableOneDao.save(t);
	}

	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public TableOne update(TableOne t) throws Exception {
		return tableOneDao.update(t);
	}

	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public void delete(TableOne t) throws Exception {
		tableOneDao.delete(t);
	}

	@Override
	public List sqlQuery(String sql) throws Exception {
		return tableOneDao.sqlQuery(sql);
	}

	@Transactional(DataSourceConfig.CENTER_1_TM)
	@Override
	public int sqlPersist(String sql) throws Exception {
		return tableOneDao.sqlPersist(sql);
	}

	@Override
	public void loopThrough() throws Exception {
		System.out.println("all record count : " + tableOneDao.countAll());
		List<TableOne> list;
		int offset = 0;
		int length = 2;
		while((list = tableOneDao.findAll(offset, length)).size()>0){
			System.out.println("loop offset : "+offset+", length : "+length);
			for(TableOne one: list){
				System.out.println(one.getId()+"_"+one.getCol1());
			}
			offset+=list.size();
		}
	}

}
