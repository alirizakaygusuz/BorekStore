package com.alirizakaygusuz.service;

import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.AuthResponse;
import com.alirizakaygusuz.dto.DtoUser;

public interface IAuthenticationService {

	
	public DtoUser registerUser(AuthRequest authRequest);
	
	
	public AuthResponse authenticate(AuthRequest authRequest);
}
