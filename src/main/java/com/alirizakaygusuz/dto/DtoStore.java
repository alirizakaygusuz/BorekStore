package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a store entity that sells böreks")
public class DtoStore extends DtoBase {

    @Schema(description = "Name of the store", example = "Borekci Ali")
    private String name;

    @Schema(description = "Address of the store")
    private DtoAddress address;

    @Schema(description = "Account associated with the store for transactions")
    private DtoAccount account;
}

