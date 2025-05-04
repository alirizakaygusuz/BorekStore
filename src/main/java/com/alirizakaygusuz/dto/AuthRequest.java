package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Payload for user login or registration")
public class AuthRequest {

    @Schema(description = "Username of the user", example = "ally")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "Password of the user", example = "P@ssw0rd123")
    @NotBlank(message = "Password cannot be blank")
    private String password;

}