package com.hy.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@GetMapping("/hello")
	@PreAuthorize("hasAuthority('hello')")
	public String hello() {
		return "Hello SpringSecurity!";
	}
	
	
	@RequestMapping("/center/redirect")
	@ResponseBody
	public String  redirectTo() {
		return "Hello 成功跳转到我这里了!";
	}
}
