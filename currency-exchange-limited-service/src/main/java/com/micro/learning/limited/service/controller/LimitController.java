package com.micro.learning.limited.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.learning.limited.service.bean.Limits;
import com.micro.learning.limited.service.configuration.ConfigurationLoader;

@RestController
public class LimitController {
	
	@Autowired
	private ConfigurationLoader configurationLoader;

	@GetMapping("/limits")
	public Limits retriveLimits()
	{
		return new Limits(configurationLoader.getMinimum(),configurationLoader.getMaximum());
	}
}
