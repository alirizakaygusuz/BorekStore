package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Input DTO for creating or updating a store")
public class DtoStoreIU {

    @Schema(description = "Name of the store", example = "Börekçi Ali", required = true)
    @NotBlank(message = "Store name cannot be blank")
    private String name;

    @Schema(description = "ID of the address associated with the store", example = "5", required = true)
    @NotNull(message = "Address ID cannot be null")
    private Long addressId;

    @Schema(description = "ID of the financial account linked to the store", example = "3", required = true)
    @NotNull(message = "Account ID cannot be null")
    private Long accountId;
}
