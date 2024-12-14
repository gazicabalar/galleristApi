package com.gazicabalar.service.impl;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gazicabalar.dto.AuthRequest;
import com.gazicabalar.dto.AuthResponse;
import com.gazicabalar.dto.DtoUser;
import com.gazicabalar.dto.RefreshTokenRequest;
import com.gazicabalar.exception.BaseException;
import com.gazicabalar.exception.ErrorMessage;
import com.gazicabalar.exception.MessageType;
import com.gazicabalar.jwt.JWTService;
import com.gazicabalar.model.RefreshToken;
import com.gazicabalar.model.User;
import com.gazicabalar.repository.RefreshTokenRepository;
import com.gazicabalar.repository.UserRepository;
import com.gazicabalar.service.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	
	private User createUser(AuthRequest input) {
		User user = new User();
		
		user.setCreatTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		
		refreshToken.setCreatTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser = new DtoUser();
		User savedUser = userRepository.save(createUser(input));
		
		BeanUtils.copyProperties(savedUser, dtoUser);
		
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
	
			authenticationProvider.authenticate(authenticationToken);	
			
			Optional<User> optional = userRepository.findByUsername(input.getUsername());
			
			String accessToken = jwtService.generateToken(optional.get());
			RefreshToken refreshToken = createRefreshToken(optional.get());
			
			RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
			
			return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID,e.getMessage()));
		}
		
	}
	
	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
		
		if(optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
		}
		
		if(!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));
		}
		
		User user = optRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken refreshToken = createRefreshToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

	
	
	
}
