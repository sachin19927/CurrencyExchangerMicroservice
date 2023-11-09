package com.micro.learning.currency.conversion.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.micro.learning.currency.conversion.feign.proxy.ExchangeCurrencyProxy;
import com.micro.learning.currency.conversion.model.CurrencyConversion;
import com.micro.learning.currency.conversion.webclient.ExchangeCurrency;
import com.micro.learning.currency.conversion.webclient.HttpClient;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {
	
	private final HttpClient httpClient;
	private final ExchangeCurrencyProxy exchangeCurrencyProxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exchange/from/{from}/to/{to}",CurrencyConversion.class,uriVariables);
		
		var currencyConversion = responseEntity.getBody();
		
		return new CurrencyConversion(currencyConversion.getId(), currencyConversion.getFrom(), currencyConversion.getTo(), currencyConversion.getConversionMultiple(), quantity, 
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment()+ " - resttemplate");
	}
	
	
	@GetMapping("/currency-conversion-webclient/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionWebClient(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		var exchangeCurrency = httpClient.retrieveExchangeValue(from, to);
		
		return new CurrencyConversion(exchangeCurrency.id(), exchangeCurrency.from(), exchangeCurrency.to(), exchangeCurrency.conversionMultiple(), quantity, 
				quantity.multiply(exchangeCurrency.conversionMultiple()), exchangeCurrency.environment()+ " - webclient");
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		var currencyConversion = exchangeCurrencyProxy.retrieveExchangeValue(from, to);
		
		return new CurrencyConversion(currencyConversion.getId(), currencyConversion.getFrom(), currencyConversion.getTo(),currencyConversion.getConversionMultiple(),quantity, 
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment()+" - Feign");
	}

}