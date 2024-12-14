package com.gazicabalar.service;

import com.gazicabalar.dto.AuthRequest;
import com.gazicabalar.dto.AuthResponse;
import com.gazicabalar.dto.DtoUser;
import com.gazicabalar.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
	
}
