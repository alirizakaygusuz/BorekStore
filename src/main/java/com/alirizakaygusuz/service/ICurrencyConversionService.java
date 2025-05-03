package com.alirizakaygusuz.service;

import java.math.BigDecimal;

public interface ICurrencyConversionService {

	BigDecimal convertToAud(String currencyType, double priceInAUD);
}
