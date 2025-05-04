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
@Schema(description = "Represents the association between a store and a börek it sells")
public class DtoStoreBorek extends DtoBase {

    @Schema(description = "Store that sells the börek")
    private DtoStore store;

    @Schema(description = "Börek offered by the store")
    private DtoBorek borek;
}
