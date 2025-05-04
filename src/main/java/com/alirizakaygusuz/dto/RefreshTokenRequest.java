package com.alirizakaygusuz.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload used to refresh an access token using a valid refresh token")
public class RefreshTokenRequest {

    @NotBlank(message = "Refresh token cannot be blank")
    @Schema(
        description = "Valid refresh token obtained from a previous authentication response or refresh token response",
        example = "9e8a7055-c897-4760-8d2f-c4f9d5146962"
    )
    private String refreshToken;
}
