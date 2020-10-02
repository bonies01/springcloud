package com.hy.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
	
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello,笨蛋，你调到我了,傻啊";		
	}
	
	@RequestMapping("/redirectHello")
	public String redirectHello() {
		return "我是hello重新路由后的页面";		
	}
}
