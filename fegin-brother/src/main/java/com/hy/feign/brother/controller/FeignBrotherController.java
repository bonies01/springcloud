package com.hy.feign.brother.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fegin/brother")
public class FeignBrotherController {

		@GetMapping("test1")
		public String test1() {
			return "我是中文test1,小屁屁你调到我了,哈哈!";
		}
}
