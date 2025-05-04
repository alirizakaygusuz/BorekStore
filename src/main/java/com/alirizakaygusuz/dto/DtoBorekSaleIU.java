package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Input DTO for creating a börek sale transaction")
public class DtoBorekSaleIU {

    @Schema(description = "ID of the store selling the börek", example = "2", required = true)
    @NotNull(message = "Store Id cannot be null")
    @Positive(message = "Store Id must be a positive value")
    private Long storeId;

    @Schema(description = "ID of the börek being sold", example = "7", required = true)
    @NotNull(message = "Borek Id cannot be null")
    @Positive(message = "Borek Id must be a positive value")
    private Long borekId;

    @Schema(description = "ID of the customer buying the börek", example = "5", required = true)
    @NotNull(message = "Customer Id cannot be null")
    @Positive(message = "Customer Id must be a positive value")
    private Long customerId;
}
