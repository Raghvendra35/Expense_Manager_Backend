package com.expense.doc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class Documentations {

	// This class is used to understand the project structure
	/*
	 * we use @CrossOrigin("*") on Controller level but we can also use this thing
	 * as class level when we use class level then we remove from Controller level
	 * like That
	 * 
	 * @CrossOrigin("*") public class ExpenseController { }
	 * 
	 * Class level -------------------
	 * 
	 * @Configuration public class CorsConfig {
	 * 
	 * @Bean public CorsFilter corsFilter() 
	 * { 
	 * UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource(); CorsConfiguration config=new
	 * CorsConfiguration(); config.setAllowCredentials(true);
	 * config.setAllowedOriginPatterns(List.of("http://localhost:8080"));
	 * config.addAllowedMethod("*"); config.addAllowedHeader("*");
	 * source.registerCorsConfiguration("/**", config); 
	 * return new CorsFilter(source); 
	 * }
	 * 
	 * if we use class level then remove from Controller level
	 * 
	 */
}
