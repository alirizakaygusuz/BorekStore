package com.alirizakaygusuz.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.alirizakaygusuz.entity.Address;
import com.alirizakaygusuz.enums.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoAccount extends DtoBase {
	
	
	private Address address;

	private String firstName;

	private String lastName;

	private String identityNumber;

	private LocalDate birthDate;

	private String cardNo;

	private BigDecimal amount;

	private CurrencyType currencyType;

	private String bankName;

	private BigDecimal limitOfCard;

}
