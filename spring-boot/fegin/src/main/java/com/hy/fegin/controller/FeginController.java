package com.hy.fegin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hy.fegin.service.FeignService;

@RestController
@RequestMapping("/fegin")
public class FeginController {
	
	
	@Autowired
	private FeignService service;
	@GetMapping("/test1")
	public String test1() {
		return service.test1();
		
	}
}
