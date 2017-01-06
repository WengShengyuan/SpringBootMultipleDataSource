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
import com.rails.demoapp.core.module.tbdstwo.domain.TableTwo;
import com.rails.demoapp.core.module.tbdstwo.service.TableTwoService;

import ch.qos.logback.core.net.server.Client;

@Controller
@RequestMapping("/api/secondary")
public class SecondaryAPI {

	@Resource(name = "TableTwoServiceImpl")
	TableTwoService tableTwoService;
	
	@RequestMapping(value="date",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean test (HttpServletRequest request, HttpServletResponse response){
		ResultBean bean = new ResultBean();
		bean.add("Date", new Date().toString());
		return bean;
	}
	
	@RequestMapping(value="query",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean query(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id){
		ResultBean bean = new ResultBean();
		try {
			TableTwo result = tableTwoService.findById(id);
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
			List<TableTwo> result = tableTwoService.findAll();
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
			TableTwo entity = new TableTwo();
			entity.setCol1(value);
			TableTwo result = tableTwoService.save(entity);
			bean.add("result", result);
		} catch (Exception e) {
			e.printStackTrace();
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
			List<TableTwo> entities = tableTwoService.queryByMap(map);
			if(entities == null || entities.size()<1){
				bean.error("can not find any match.");
				return bean;
			}
			for(TableTwo entity:entities){
				entity.setCol1(newValue);
				TableTwo result = tableTwoService.save(entity);
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
			List<TableTwo> entities = tableTwoService.queryByMap(map);
			if(entities == null || entities.size()<1){
				bean.error("can not find any match.");
				return bean;
			}
			for(TableTwo entity:entities){
				tableTwoService.delete(entity);
			}
			bean.add("result", "success : "+entities.size());
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}
	
}
