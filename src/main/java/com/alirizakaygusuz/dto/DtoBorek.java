package com.alirizakaygusuz.dto;

import java.math.BigDecimal;

import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.enums.BorekType;
import com.alirizakaygusuz.enums.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBorek extends DtoBase {
	

    private BorekType borekType;

    private BigDecimal price;

    
    private CurrencyType currencyType ; 

    private BorekStatus borekStatus;

}
