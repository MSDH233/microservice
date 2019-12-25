package com.fujinran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.builder.SpringApplicationBuilder;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan("com.fujinran.mapper")
public class OauthApplication {

	public static void main(String[] args) {

		SpringApplication application =  new SpringApplicationBuilder(OauthApplication.class).build();
		application.run(args);


	}

}
