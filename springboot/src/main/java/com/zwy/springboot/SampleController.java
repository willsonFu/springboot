package com.zwy.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController extends BaseController{

	@RequestMapping("/")
	String home() {
		return "hello world";
	}
	
	@RequestMapping("/exeception")
	int zeroExeception() {
		return 100/0;
	}
	
	@RequestMapping("/hello")
	String hello() {
		return "你好，世界";
	}
}
