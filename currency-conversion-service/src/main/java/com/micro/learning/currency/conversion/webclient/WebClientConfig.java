package com.micro.learning.currency.conversion.webclient;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "http")
public class WebClientConfig {
	
	private String url;
	
	@Bean
    HttpClient spaClient(WebClient.Builder webClientBuilder) {
	
		var webClient = webClientBuilder
				.baseUrl(url)
				.defaultHeaders(httpHeeaders->httpHeeaders.addAll(createHeaders()))
				.build();
		
		var httpServiceProxyFactory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(webClient)).build();
		
		return httpServiceProxyFactory.createClient(HttpClient.class);
	}
	
	private HttpHeaders createHeaders() {
		var httpHeaders = new HttpHeaders();
		httpHeaders.add(ACCEPT,APPLICATION_JSON);
		httpHeaders.add(CONTENT_TYPE,APPLICATION_JSON);
		return httpHeaders;
	}
	
}
