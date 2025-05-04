package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Input DTO for assigning a börek to a store")
public class DtoStoreBorekIU {

    @Schema(description = "ID of the store where the börek will be sold", example = "1", required = true)
    @NotNull(message = "Store ID cannot be null")
    private Long storeId;

    @Schema(description = "ID of the börek to be added to the store's catalog", example = "4", required = true)
    @NotNull(message = "Borek ID cannot be null")
    private Long borekId;
}
