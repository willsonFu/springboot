package com.zwy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		//ctx.close();
	}
	
	/**
	 * 不使用注解：@ServletComponentScan，来注册servlet
	 * @return
	 */
//	@Bean
//	 public ServletRegistrationBean MyServlet1(){
//		return new ServletRegistrationBean(new MyServlet(), "/myServlet1/*");
//	}
//	
//	@Bean
//	public FilterRegistrationBean MyFilter1() {
//		return new FilterRegistrationBean(new MyFilter(),new ServletRegistrationBean(new MyServlet(), "/*"));
//	}
//	
//	@Bean
//	public ServletListenerRegistrationBean<MyServletContextListener> MyListener1() {
//		return new ServletListenerRegistrationBean<MyServletContextListener>(new MyServletContextListener());
//	}
//	
//	@Bean
//	public ServletListenerRegistrationBean<MyHttpSessionListener> MyListener2() {
//		return new ServletListenerRegistrationBean<MyHttpSessionListener>(new MyHttpSessionListener());
//	}
	
//	@Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//        };
//	}
	
	@Bean
	public HttpMessageConverters jacksonHttpMessageConverters() {
		FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
		jsonConverter.setFastJsonConfig(config);
		return new HttpMessageConverters(jsonConverter);
	}
}
