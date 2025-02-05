package com.expense;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerBackendApplication.class, args);
		System.out.println("Running...");
	}
	
	//ModelMapper
	@Bean
	public ModelMapper medelMapper()
	{
		return new ModelMapper();
	}

}
