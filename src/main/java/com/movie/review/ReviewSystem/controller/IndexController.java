package com.movie.review.ReviewSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class IndexController {
	
//	@Autowired
//	IndexService service;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		return "index";
	}
	
}
