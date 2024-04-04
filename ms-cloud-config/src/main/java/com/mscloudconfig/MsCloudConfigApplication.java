package com.mscloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCloudConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsCloudConfigApplication.class, args);
	}

}
