package com.kannect.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.kannect.user.service.masters.service")
public class KannectUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KannectUserServiceApplication.class, args);
	}

}
