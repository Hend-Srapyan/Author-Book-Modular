package com.example.authorbookweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.authorbookweb", "com.example.authorbookcommon"})
@EntityScan(basePackages = {"com.example.authorbookcommon.entity"})
public class AuthorBookWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorBookWebApplication.class, args);
	}

}
