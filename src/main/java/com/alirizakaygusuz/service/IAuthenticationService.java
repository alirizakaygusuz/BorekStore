package com.alirizakaygusuz.service;

import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.AuthResponse;
import com.alirizakaygusuz.dto.DtoUser;
import com.alirizakaygusuz.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	
	public DtoUser registerUser(AuthRequest authRequest);
	
	
	public AuthResponse authenticate(AuthRequest authRequest);
	
	
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}

