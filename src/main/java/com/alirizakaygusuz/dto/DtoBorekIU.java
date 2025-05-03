package com.alirizakaygusuz.dto;

import java.math.BigDecimal;

import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.enums.BorekType;

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
public class DtoBorekIU {

	@NotNull
	private BorekType borekType;

	@NotNull
	@Positive
	private BigDecimal price;
	
	@NotNull
    private BorekStatus borekStatus;


	
	

}
