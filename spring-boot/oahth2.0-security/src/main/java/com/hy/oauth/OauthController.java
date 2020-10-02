package com.hy.oauth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center")
public class OauthController {
	
	@RequestMapping("/redirect")
	public String redirectTo() {
		return "你咋还重定向到我这里来了呢?";
		
	}

}
