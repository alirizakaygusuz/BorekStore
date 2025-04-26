package com.alirizakaygusuz.controller;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.DtoUser;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest authRequest);
}
