package com.example.backend_spring_boot_final_project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@EnableWebSecurity
//@EnableMethodSecurity
public class BackendSpringBootFinalProjectApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendSpringBootFinalProjectApplication.class, args);
	}

//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

}
