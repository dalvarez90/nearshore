package com.banamex.nearshore.catalogsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.banamex.nearshore.*")
public class NearshoreCatalogsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NearshoreCatalogsmsApplication.class, args);
	}
}
