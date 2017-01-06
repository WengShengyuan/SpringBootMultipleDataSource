package com.rails.demoapp.controller.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rails.demoapp.core.common.wrapper.ResultBean;
import com.rails.demoapp.core.module.tbdsboth.domain.TableThree;
import com.rails.demoapp.core.module.tbdsboth.service.TableThreeService;

@Controller
@RequestMapping("/api/both")
public class BothAPI {

	@Resource(name = "PrimaryTableThreeServiceImpl")
	TableThreeService primaryTableThreeService;
	
	@Resource(name = "SecondaryTableThreeServiceImpl")
	TableThreeService secondaryTableThreeService;
	
	@RequestMapping(value="save",method = RequestMethod.GET)
	@ResponseBody
	public ResultBean save(HttpServletRequest request, HttpServletResponse response,@RequestParam("target") String target,@RequestParam("value") String value){
		ResultBean bean = new ResultBean();
		if("1".equals(target)){
			TableThree entity = new TableThree();
			entity.setCol1(value);
			TableThree result=null;
			try {
				result = primaryTableThreeService.save(entity);
			} catch (Exception e) {
				bean.error(e.getMessage());
			}
			bean.add("result", result);
			return bean;
		}else if("2".equals(target)){
			TableThree entity = new TableThree();
			entity.setCol1(value);
			TableThree result=null;
			try {
				result = secondaryTableThreeService.save(entity);
			} catch (Exception e) {
				bean.error(e.getMessage());
			}
			bean.add("result", result);
			return bean;
		}else {
			bean.error("target is invalid.");
		}
		return bean;
	}
	
	@RequestMapping(value="query-all",method=RequestMethod.GET)
	@ResponseBody
	public ResultBean queryAll(HttpServletRequest request, HttpServletResponse response,@RequestParam("target") String target){
		ResultBean bean = new ResultBean();
		try {
			if("1".equals(target)){
				List<TableThree> result = primaryTableThreeService.findAll();
				bean.add("result", result);
			} else if("2".equals(target)){
				List<TableThree> result = secondaryTableThreeService.findAll();
				bean.add("result", result);
			}else {
				bean.error("target is invalid.");
			}
		} catch (Exception e) {
			bean.error(e.getMessage());
		}
		return bean;
	}
}
