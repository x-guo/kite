package com.example.geney;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableCaching
@SpringBootApplication
public class KiteApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(KiteApplication.class).web(true).run(args);
		//SpringApplication.run(KiteApplication.class, args);
	}
}
