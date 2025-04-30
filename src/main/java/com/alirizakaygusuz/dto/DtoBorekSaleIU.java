package com.alirizakaygusuz.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBorekSaleIU {


	@NotNull(message = "Store Id can not be null ")
	@Positive(message = "Store Id must be a positive value ")
	private Long storeId;
	
	@NotNull(message = "Borek Id can not be null ")
	@Positive(message = "Borek Id must be a positive value")
	private Long borekId;

	@NotNull(message = "Customer ID can not be null ")
	@Positive(message = "Customer Id must be a positive value")
	private Long customerId;
	


}
