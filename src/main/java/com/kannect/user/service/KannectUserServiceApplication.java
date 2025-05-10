package com.kannect.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kannect.user.service")
@EntityScan(basePackages = "com.kannect.user.service")
@EnableFeignClients(basePackages = "com.kannect.user.service.masters.service")
public class KannectUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KannectUserServiceApplication.class, args);
	}

}
