package com.alirizakaygusuz.controller;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoCurrencyRate;

public interface IRestCurrencyRateController {
	 
	 RootEntity<DtoCurrencyRate> getExchangeRates();
}
