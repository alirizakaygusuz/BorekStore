package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a Börek sale transaction between a customer and a store")
public class DtoBorekSale extends DtoBase {

    @Schema(description = "Store that sold the börek")
    private DtoStore store;

    @Schema(description = "Börek item that was sold")
    private DtoBorek borek;

    @Schema(description = "Customer who purchased the börek")
    private DtoCustomer customer;

    @Schema(description = "Account used in the transaction")
    private DtoAccount account; 
}

