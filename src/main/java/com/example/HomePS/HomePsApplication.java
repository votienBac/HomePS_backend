package com.example.HomePS;

import com.example.HomePS.model.AppUser;
import com.example.HomePS.model.Role;
import com.example.HomePS.service.AppUserService;
import com.example.HomePS.configuration.SwaggerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class HomePsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomePsApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	}
