package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gazicabalar.controller.IRestAuthenticationController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.AuthRequest;
import com.gazicabalar.dto.AuthResponse;
import com.gazicabalar.dto.DtoUser;
import com.gazicabalar.dto.RefreshTokenRequest;
import com.gazicabalar.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController{

	@Autowired
	private IAuthenticationService authenticationService;
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.register(input));
	}

	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.authenticate(input));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
		return ok(authenticationService.refreshToken(input));
	}

	
	
}
