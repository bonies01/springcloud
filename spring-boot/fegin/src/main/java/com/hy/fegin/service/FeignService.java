package com.hy.fegin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="fegin-brother-client")
public interface FeignService {
		
		@RequestMapping("/fegin/brother/test1")
		String test1();
}
