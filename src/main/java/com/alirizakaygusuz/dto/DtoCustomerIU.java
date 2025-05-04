package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Input DTO for creating or updating a customer and assigning related accounts")
public class DtoCustomerIU {

    @Schema(description = "Full name or nickname of the customer", example = "Ali Rıza Kaygusuz", required = true)
    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;

    @Schema(description = "List of account IDs to be associated with the customer", example = "[1, 2, 3]", required = true)
    @NotNull(message = "Account ID list cannot be null")
    private Set<Long> accountIds;
}
