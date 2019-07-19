package com.mbm.springbapirest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages="com.mbm.springbapirest.models")
public class SpringBootAngularApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularApiRestApplication.class, args);
	}

}
