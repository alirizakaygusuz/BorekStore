package com.alirizakaygusuz.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestCurrencyRateController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoCurrencyRate;
import com.alirizakaygusuz.service.ICurrencyRateService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/rest/api/currency")
public class RestCurrencyRateController extends RestBaseController implements IRestCurrencyRateController {

    @Autowired
    private ICurrencyRateService currencyRateService;

    @Operation(summary = "Get current exchange rates")
    @GetMapping("/rates")
    @Override
    public RootEntity<DtoCurrencyRate> getExchangeRates() {
        return ok(currencyRateService.getExchangeRates());
    }
}
