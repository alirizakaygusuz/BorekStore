package com.alirizakaygusuz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestCurrencyRateController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.entity.CurrencyRate;
import com.alirizakaygusuz.service.ICurrencyRateService;

@RestController
@RequestMapping("/rest/api/currency")
public class RestCurrencyRateController extends RestBaseController implements IRestCurrencyRateController {

	@Autowired
	private ICurrencyRateService currencyRateService;
	
	@GetMapping("/rates")
	@Override
	public RootEntity<CurrencyRate> getExchangeRates() {
		
		return ok(currencyRateService.getExchangeRates());
	}

}
