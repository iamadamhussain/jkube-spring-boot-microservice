package com.fss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoBApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoBApplication.class, args);
	}

	@Bean
	// @LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
