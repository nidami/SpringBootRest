package com.deew.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deew.AppServer.authentication.JwtAuthFilter;

@Configuration
public class ApplicationConfig {
	
	/*@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	public FilterRegistrationBean<JwtAuthFilter> authFilter() {
		FilterRegistrationBean<JwtAuthFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(jwtAuthFilter);
		filterBean.addUrlPatterns("/api/*","/auth/*");
		return filterBean;
		
	}*/
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
