package com.moviewreview.springboot.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.moviewreview.springboot.web.service.IndexService;
import com.sun.syndication.io.FeedException;

@Controller
public class IndexController {
	
	@Autowired
	IndexService service;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) throws IllegalArgumentException, FeedException, IOException{
		return "index";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/postData", method = RequestMethod.POST)
	public ResponseEntity postData(@RequestBody String headers,HttpServletRequest request) throws MalformedURLException, IllegalArgumentException, IOException, FeedException{
		Map userDetailMap = new Gson().fromJson(headers, Map.class);
		Map<String,Object> headerMap = (Map<String, Object>) userDetailMap.get("headers");
		String city = (String) headerMap.get("cityname");
//		boolean postMessage = service.postRssData(city);
		Map<String,Object> returnMap = new HashMap<>();
		return new ResponseEntity(returnMap, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/validateUser", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity validateUser(HttpServletRequest request) throws ClassNotFoundException {
		Map recordMap = new HashMap<>();
		Map paramMap = new HashMap<>();
		Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            recordMap.put(key, value);
        }
        String username =(String) recordMap.get("username");
        String password=(String) recordMap.get("password");
        paramMap.put("username", username);
        paramMap.put("password", password);
		Map<String,Object> returnMap = service.validateUser(paramMap);
		return new ResponseEntity(returnMap, HttpStatus.OK);
	}
	
	
}
