package com.alirizakaygusuz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestAuthenticationController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.AuthResponse;
import com.alirizakaygusuz.dto.DtoUser;
import com.alirizakaygusuz.dto.RefreshTokenRequest;
import com.alirizakaygusuz.service.IAuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	
		
	@Operation(
	        summary = "Register a new user",
	        description = "Creates a new user account using username and password."
	    )
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {
		return ok(authenticationService.registerUser(authRequest));
	}


	@Operation(
		    summary = "Authenticate and obtain JWT token",
		    description = "Send username and password to receive an access and refresh token."
		)
	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) {
		
		return ok(authenticationService.authenticate(authRequest));
	}

	@Operation(
		    summary = "Refresh access token that is expired",
		    description = "Send refresh token to receive a new access."
		)
	@PostMapping("/refresh-token")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		
		return ok(authenticationService.refreshToken(refreshTokenRequest));
	}

}
