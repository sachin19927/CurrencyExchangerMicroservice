package com.micro.learning.limited.service.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("limits.service")
@Getter
@Setter
public class ConfigurationLoader {

	private int minimum;
	private int maximum;
	
}
