package com.alirizakaygusuz.controller;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.entity.CurrencyRate;

public interface IRestCurrencyRateController {
	 
	 RootEntity<CurrencyRate> getExchangeRates();
}
