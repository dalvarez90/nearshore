package com.banamex.nearshore.employeesms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.banamex.nearshore.*")
public class NearshoreEmployeesmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NearshoreEmployeesmsApplication.class, args);
	}
}
