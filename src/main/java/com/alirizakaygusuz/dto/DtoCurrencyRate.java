package com.alirizakaygusuz.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class DtoCurrencyRate {

	
	@JsonProperty("result")
	private String result;
	
	
	
	
	@JsonProperty("conversion_rates")
	private Map<String, Double> conversionRates;
	
}
