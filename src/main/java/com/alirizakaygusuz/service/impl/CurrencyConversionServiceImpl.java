package com.alirizakaygusuz.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.entity.CurrencyRate;
import com.alirizakaygusuz.service.ICurrencyConversionService;
import com.alirizakaygusuz.service.ICurrencyRateService;

@Service
public class CurrencyConversionServiceImpl implements ICurrencyConversionService {

	@Autowired
	private ICurrencyRateService currencyRateService;
	
	@Override
	public BigDecimal convertToAud(String currencyType, double priceInAUD) {
		CurrencyRate currencyRate = currencyRateService.getExchangeRates();
        double usdToCurrencyTypeRate = currencyRate.getConversionRates().get(currencyType);
        
        
        return BigDecimal.valueOf(priceInAUD).multiply(BigDecimal.valueOf(usdToCurrencyTypeRate));
	}

}
