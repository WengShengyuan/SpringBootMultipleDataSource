package com.rails.demoapp.controller.view;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rails.demoapp.core.module.tbdsone.domain.TableOne;
import com.rails.demoapp.core.module.tbdsone.service.TableOneService;

@Controller
@RequestMapping("/view")
public class TestView {
	
	@Resource(name="TableOneServiceImpl")
	private TableOneService tableOneService;
	
	
	@RequestMapping("date")
	public ModelAndView date(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.addObject("date", new Date().toString());
		try {
			List<TableOne> results = tableOneService.findAll();
			view.addObject("results", results);
		} catch (Exception e) {
		}
		
		view.setViewName("index");
		return view;
	}
	

}
