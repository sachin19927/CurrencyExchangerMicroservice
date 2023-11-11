package com.micro.learning.currency.conversion.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.micro.learning.currency.conversion.model.CurrencyConversion;

//@FeignClient(name = "currency-exchange", url = "http://localhost:8001")
@FeignClient(name = "CURRENCY-EXCHANGE-SERVICE")
public interface ExchangeCurrencyProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from,
			@PathVariable String to);
}
