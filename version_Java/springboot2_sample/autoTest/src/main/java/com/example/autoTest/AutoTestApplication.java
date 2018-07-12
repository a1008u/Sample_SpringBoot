package com.example.autoTest;

import com.example.autoTest.domain.Car;
import com.example.autoTest.repository.CarRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AutoTestApplication {

	@Autowired
	private CarRepository repository;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AutoTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AutoTestApplication.class);
		logger.info("Hello Spring Boot");


	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			// Save demo data to database
			repository.save(new Car("Ford", "Mustang", "Red",
							"ADF-1121", 2017, 59000));
			repository.save(new Car("Nissan", "Leaf", "White",
							"SSJ-3002", 2014, 29000));
			repository.save(new Car("Toyota", "Prius", "Silver",
							"KKO-0212", 2018, 39000));
			repository
				.findAll()
				.forEach(i -> System.out.println(i.getColor()));
		};
	}
}
