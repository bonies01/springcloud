package com.hy.hystrix.service;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;

/**
 * @author Administrator
 *
 */
@Service
public class HystrixServiceImpl implements HystrixService{
	/**
	 * 测试兜底方法
	 */
	@HystrixCommand(fallbackMethod="test1CallBack")
	@Override
	public String test1() {
		throw new RuntimeException();
		
		//return "服务正常";
	}
	/**
	 * 熔断	
	 * @return
	 */
	@HystrixCommand(fallbackMethod="callBack",commandProperties= {
			// 默认20个;10ms内请求数大于20个时就启动熔断器，当请求符合熔断条件时将触发getFallback()。
			@HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value="10"),
			// 请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件时将触发getFallback()。
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value="50"),
            // 默认5秒;熔断多少秒后去尝试请求
            @HystrixProperty(name=HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, 
                        value="5000")
	})
	public String test2() {
		return "服务正常";
		
	}
	public String test1CallBack() {
		return "我是callBack 兜底方法，你扛不住了来找我，哈哈哈";
		
	}
	
	

}
