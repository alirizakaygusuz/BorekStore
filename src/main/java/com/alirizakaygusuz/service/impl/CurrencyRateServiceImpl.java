package com.alirizakaygusuz.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alirizakaygusuz.dto.DtoCurrencyRate;
import com.alirizakaygusuz.service.ICurrencyRateService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements ICurrencyRateService {
	
    private final RestTemplate restTemplate;


	@Value("${api.key}")
	private String apiKey;
	
	
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/{apiKey}/latest/AUD";
	
    public DtoCurrencyRate getExchangeRates() {
        String url = API_URL.replace("{apiKey}", apiKey);

        return restTemplate.getForObject(url, DtoCurrencyRate.class);
    }
    
    
    
	
}
