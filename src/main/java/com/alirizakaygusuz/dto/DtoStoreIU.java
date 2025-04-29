package com.alirizakaygusuz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStoreIU {

	@NotBlank
	private String name;
	
	@NotNull
	private Long addressId;
	
	@NotNull
	private Long accountId;
}
