package com.zwy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zwy.interceptor.AuthInterceptor;

@Configuration
public class AuthWebAppConfigurer extends WebMvcConfigurerAdapter {
	
	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor()).addPathPatterns("/books/**").addPathPatterns("/account/**").addPathPatterns("/user/**")
				.excludePathPatterns("/user/login");
		super.addInterceptors(registry);
	}

}
