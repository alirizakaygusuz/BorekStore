package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Basic user information, typically used for registration or login")
public class DtoUser extends DtoBase {

    @Schema(description = "Unique username of the user", example = "example@hotmail.com")
    private String username;

    @Schema(description = "Plain text password", example = "P@ssw0rd123")
    private String password;
}
