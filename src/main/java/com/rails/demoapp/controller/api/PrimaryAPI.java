package com.rails.demoapp.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rails.demoapp.core.common.wrapper.ResultBean;
import com.rails.demoapp.core.module.tbdsone.domain.TableOne;
import com.rails.demoapp.core.module.tbdsone.service.TableOneService;

@Controller
@RequestMapping("/api/primary")
public class PrimaryAPI {
	
	@Resource(name = "TableOneServiceImpl")
	TableOneService tableOneService;
	
	@RequestMapping(value="date",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean test (HttpServletRequest request, HttpServletResponse response){
		ResultBean bean = new ResultBean();
		bean.add("Date", new Date().toString());
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="sql-test",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean sqlTest(HttpServletRequest request, HttpServletResponse response){
		ResultBean bean = new ResultBean();
		int i = -1;
		try {
			i = tableOneService.sqlPersist("INSERT INTO ds_one_tb_one (id,col1) values (33,'testvalue');");
			bean.add("results", i);
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean query(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id){
		ResultBean bean = new ResultBean();
		try {
			TableOne result = tableOneService.findById(id);
			bean.add("result", result);
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping(value="query-all",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean queryAll(HttpServletRequest request, HttpServletResponse response){
		ResultBean bean = new ResultBean();
		try {
			List<TableOne> result = tableOneService.findAll();
			bean.add("result", result);
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping(value="save",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean save(HttpServletRequest request, HttpServletResponse response,@RequestParam("value") String value){
		ResultBean bean = new ResultBean();
		try {
			TableOne entity = new TableOne();
			entity.setCol1(value);
			TableOne result = tableOneService.save(entity);
			bean.add("result", result);
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping(value="update",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean update(HttpServletRequest request, HttpServletResponse response,@RequestParam("oldValue") String oldValue, @RequestParam("newValue") String newValue){
		ResultBean bean = new ResultBean();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("col1", oldValue);
			List<TableOne> entities = tableOneService.queryByMap(map);
			if(entities == null || entities.size()<1){
				bean.error("can not find any match.");
				return bean;
			}
			for(TableOne entity:entities){
				entity.setCol1(newValue);
				TableOne result = tableOneService.update(entity);
			}
			bean.add("result", "success : "+entities.size());
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean delete(HttpServletRequest request, HttpServletResponse response,@RequestParam("value") String value){
		ResultBean bean = new ResultBean();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("col1", value);
			List<TableOne> entities = tableOneService.queryByMap(map);
			if(entities == null || entities.size()<1){
				bean.error("can not find any match.");
				return bean;
			}
			for(TableOne entity:entities){
				tableOneService.delete(entity);
			}
			bean.add("result", "success : "+entities.size());
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}

}
