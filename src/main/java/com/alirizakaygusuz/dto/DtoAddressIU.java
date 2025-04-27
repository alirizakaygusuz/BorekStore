package com.alirizakaygusuz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddressIU {
	
	@NotBlank
	public String country;

	@NotBlank
	public String city;

	@NotBlank
	public String state;

	@NotBlank
	public String street;

	@NotBlank
	public String postalCode;


}
