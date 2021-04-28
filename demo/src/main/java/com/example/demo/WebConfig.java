package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000")
			.maxAge(3600)
			.allowedMethods(
				HttpMethod.GET.name(), 
				HttpMethod.POST.name(), 
				HttpMethod.PUT.name(), 
				HttpMethod.PATCH.name(),
				HttpMethod.DELETE.name())
			.maxAge(3600);
	}
}
