package com.alirizakaygusuz.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Address information associated with an account,customer and store")
public class DtoAddress extends DtoBase {

    @Schema(description = "Country name", example = "Turkey")
    private String country;

    @Schema(description = "City name", example = "Izmir")
    private String city;

    @Schema(description = "State or province", example = "Aegean")
    private String state;

    @Schema(description = "Street address", example = "Izmir Caddesi No:15")
    private String street;

    @Schema(description = "Postal code or ZIP code", example = "35000")
    private String postalCode;
}
