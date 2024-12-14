package com.gazicabalar.controller;

import com.gazicabalar.dto.AuthRequest;
import com.gazicabalar.dto.AuthResponse;
import com.gazicabalar.dto.DtoUser;
import com.gazicabalar.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
	
}
