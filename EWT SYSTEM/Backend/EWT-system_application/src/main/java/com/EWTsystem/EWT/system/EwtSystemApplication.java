package com.EWTsystem.EWT.system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EwtSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(EwtSystemApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){return new ModelMapper();}
}
