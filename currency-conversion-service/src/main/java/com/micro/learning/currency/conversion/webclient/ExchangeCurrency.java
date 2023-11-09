package com.micro.learning.currency.conversion.webclient;

import java.math.BigDecimal;

public record ExchangeCurrency(Long id,String from,
		String to,BigDecimal conversionMultiple,String environment) {
	
}