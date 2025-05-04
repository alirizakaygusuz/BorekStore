package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Input DTO for creating or updating an address")
public class DtoAddressIU {

    @Schema(description = "Country name", example = "Turkey", required = true)
    @NotBlank(message = "Country cannot be blank")
    private String country;

    @Schema(description = "City name", example = "Izmir", required = true)
    @NotBlank(message = "City cannot be blank")
    private String city;

    @Schema(description = "State or province", example = "Aegean", required = true)
    @NotBlank(message = "State cannot be blank")
    private String state;

    @Schema(description = "Street address", example = "Izmir Caddesi No:15", required = true)
    @NotBlank(message = "Street cannot be blank")
    private String street;

    @Schema(description = "Postal code or ZIP code", example = "35000", required = true)
    @NotBlank(message = "Postal code cannot be blank")
    private String postalCode;
}
