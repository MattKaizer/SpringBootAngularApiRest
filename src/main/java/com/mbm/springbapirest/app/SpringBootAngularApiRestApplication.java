package com.mbm.springbapirest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"com.mbm.springbapirest.models", "com.mbm.springbapirest.controllers",
		"com.mbm.springbapirest.service"})
@EntityScan(basePackages="com.mbm.springbapirest.models")
@EnableJpaRepositories("com.mbm.springbapirest.repository")
public class SpringBootAngularApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularApiRestApplication.class, args);
	}

}
