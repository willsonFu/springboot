package com.zwy.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class LucyController {

	@Autowired
	ConfigBean configBean;
	
	@Autowired
	User user;
	
	@RequestMapping("/lucy")
	String miya() {
		return configBean.getGreeting()+" >>>>"+configBean.getName()+" >>>>"+ configBean.getUuid()+" >>>>"+configBean.getMax();
	}
	
	@RequestMapping("/user")
	public String user() {
		//return "姓名： " + user.getName() + " , 年龄：" + user.getAge() ;
		return user.toString();
	}
}
