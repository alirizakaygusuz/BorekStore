package com.alirizakaygusuz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestAuthenticationController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.DtoUser;
import com.alirizakaygusuz.service.IAuthenticationService;

import jakarta.validation.Valid;


@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest authRequest) {

		System.out.println("hello krallll");
		return ok(authenticationService.registerUser(authRequest));
	}

}
