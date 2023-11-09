package com.micro.learning.currency.conversion.webclient;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface HttpClient {

	@GetExchange(url="/currency-exchange/from/{from}/to/{to}")
	ExchangeCurrency retrieveExchangeValue(@PathVariable String from,
			@PathVariable String to);
}
