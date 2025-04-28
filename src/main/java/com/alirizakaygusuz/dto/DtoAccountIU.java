package com.alirizakaygusuz.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.alirizakaygusuz.enums.CurrencyType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoAccountIU {
	
	@NotNull
	private Long address_id;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String identityNumber;

	@NotNull
	private LocalDate birthDate;

	@NotBlank
	private String cardNo;

	@NotNull
	private BigDecimal amount;

	@NotNull
	private CurrencyType currencyType;

	@NotBlank
	private String bankName;

	@NotNull
	private BigDecimal limitOfCard;

}
