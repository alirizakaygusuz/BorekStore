package com.alirizakaygusuz.dto;

import java.math.BigDecimal;

import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.enums.BorekType;
import com.alirizakaygusuz.enums.CurrencyType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Input DTO for creating or updating a börek item")
public class DtoBorekIU {

    @Schema(description = "Type of the börek to be created", example = "CHEESE_AND_SPINACH", required = true)
    @NotNull(message = "Borek type cannot be null")
    private BorekType borekType;

    @Schema(description = "Price of the börek item", example = "45.00", required = true)
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    private BigDecimal price;

    @Schema(description = "Currency type of the price", example = "TRY", required = true)
    @NotNull(message = "Currency type cannot be null")
    private CurrencyType currencyType;

    @Schema(description = "Status of the börek (e.g. SALABLE, SOLD, OUT_OF_STOCK)", example = "SALABLE", required = true)
    @NotNull(message = "Borek status cannot be null")
    private BorekStatus borekStatus;
}
