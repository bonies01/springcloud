package com.hy.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hy.hystrix.service.HystrixService;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {
	
	@Autowired
	private HystrixService service;
	
	@RequestMapping("/test1")
	public String test1() {
		return service.test1();
		
	}

}
