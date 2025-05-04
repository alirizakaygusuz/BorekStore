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
@Schema(description = "Authentication response containing the access and refresh tokens")
public class AuthResponse {

    @Schema(
        description = "Access token issued from /auth/authenticate or /auth/refresh-token endpoint",
        example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGkiLCJpYXQiOjE3NDYzNTMwNzksImV4cCI6MTc0NjM2MDI3OX0.Pro_wZKJIu9luGEK3Oovw11RRRpXrDvVeqksWE8XL-M"
    )
    private String accessToken;

    @Schema(
        description = "Refresh token used to obtain a new access token via /auth/refresh-token",
        example = "9e8a7055-c897-4760-8d2f-c4f9d5146962"
    )
    private String refreshToken;
}

