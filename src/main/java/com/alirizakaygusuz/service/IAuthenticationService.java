package com.alirizakaygusuz.service;

import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.DtoUser;

public interface IAuthenticationService {

	
	public DtoUser registerUser(AuthRequest authRequest);
}
