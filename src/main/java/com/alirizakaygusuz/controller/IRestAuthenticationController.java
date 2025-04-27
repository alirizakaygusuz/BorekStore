package com.alirizakaygusuz.controller;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.AuthResponse;
import com.alirizakaygusuz.dto.DtoUser;
import com.alirizakaygusuz.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest authRequest);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest authRequest);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
