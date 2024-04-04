package com.msproduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MsProduitServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProduitServiceApplication.class, args);
	}

}
