package com.micro.learning.currency.exchange.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CircuitBreakerConterller {
	
	
	@GetMapping
	public String sampleApi()
	{
		log.info("Sample API call");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
		return forEntity.getBody();
	}

}
