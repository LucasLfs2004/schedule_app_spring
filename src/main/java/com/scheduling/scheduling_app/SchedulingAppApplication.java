package com.scheduling.scheduling_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchedulingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingAppApplication.class, args);
	}


	@Bean
	public CommandLineRunner init() {
		return args -> {
			System.out.println("Aplicação iniciada!");
		};
	}
}
