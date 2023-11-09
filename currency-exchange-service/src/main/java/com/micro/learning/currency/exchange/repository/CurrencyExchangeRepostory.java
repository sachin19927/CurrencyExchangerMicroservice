package com.micro.learning.currency.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.learning.currency.exchange.entity.CurrencyExchange;

@Repository
public interface CurrencyExchangeRepostory extends JpaRepository<CurrencyExchange, Long> {

	CurrencyExchange findByFromAndTo(String from,String to);
}
