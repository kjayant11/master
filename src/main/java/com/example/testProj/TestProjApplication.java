package com.example.testProj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages= {"com.example.repo"})
@EntityScan(basePackages= {"com.example.entity"})
@ComponentScan(basePackages = "com.example")
@EnableAutoConfiguration
public class TestProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjApplication.class, args);
	}

}
