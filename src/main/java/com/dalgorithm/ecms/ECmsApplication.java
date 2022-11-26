package com.dalgorithm.ecms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@EnableFeignClients
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class ECmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECmsApplication.class, args);
	}

}