package com.example.server2;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer2Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EurekaServer2Application.class).web(true).run(args);
	}
}
