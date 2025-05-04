package com.alirizakaygusuz.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Customer information including name and related accounts")
public class DtoCustomer extends DtoBase {

    @Schema(description = "Full name of the customer or nickname", example = "Ali Rıza Kaygusuz - Ally")
    private String customerName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(description = "List of financial accounts associated with the customer")
    private Set<DtoAccount> accounts = new HashSet<>();
}
