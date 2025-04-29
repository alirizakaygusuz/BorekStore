package com.alirizakaygusuz.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStoreBorekIU {
	
	@NotNull
	private Long storeId;
	
	@NotNull
	private Long borekId;

}
