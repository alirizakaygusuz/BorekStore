package com.alirizakaygusuz.dto;

import java.math.BigDecimal;

import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.enums.BorekType;
import com.alirizakaygusuz.enums.CurrencyType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO representing a börek item with its type, price, currency, and status")
public class DtoBorek extends DtoBase {

    @Schema(
        description = "Type of börek to be selected from predefined options",
        example = "CHEESE_AND_SPINACH"
    )
    private BorekType borekType;

    @Schema(
        description = "Price of the börek item",
        example = "35.50"
    )
    private BigDecimal price;

    @Schema(
        description = "Currency in which the price is defined",
        example = "TRY"
    )
    private CurrencyType currencyType;

    @Schema(
        description = "Availability status of the börek item",
        example = "AVAILABLE"
    )
    private BorekStatus borekStatus;
}
