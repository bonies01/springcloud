package com.hy.oauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@GetMapping("/hello")
	@PreAuthorize("hasAuthority('hello')")
	public String hello() {
		return "Hello SpringSecurity!";
	}
}
