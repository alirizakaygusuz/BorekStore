package com.alirizakaygusuz.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.AuthResponse;
import com.alirizakaygusuz.dto.DtoUser;
import com.alirizakaygusuz.dto.RefreshTokenRequest;
import com.alirizakaygusuz.entity.RefreshToken;
import com.alirizakaygusuz.entity.User;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.jwt.JWTService;
import com.alirizakaygusuz.mapper.UserMapper;
import com.alirizakaygusuz.repository.RefreshTokenRepository;
import com.alirizakaygusuz.repository.UserRepository;
import com.alirizakaygusuz.service.IAuthenticationService;

import jakarta.transaction.Transactional;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Value("${refresh.token.expiration}")
	private Long refreshTokenExpirationMs;

	private User createUser(AuthRequest authRequest) {
		User user = new User();
		user.setUsername(authRequest.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));

		return user;
	}

	@Override
	public DtoUser registerUser(AuthRequest authRequest) {
		if (userRepository.findByUsername(authRequest.getUsername()).isPresent()) {
			throw new BaseException(new ErrorMessage(ErrorType.USERNAME_IS_ALREADY_EXISTS,
					"Username '" + authRequest.getUsername() + "' is already taken."));

		}

		User savedUser = userRepository.save(createUser(authRequest));

		return userMapper.userToUserDto(savedUser);
	}

	private RefreshToken createRefreshToken(User user) {

		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + refreshTokenExpirationMs));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);

		return refreshToken;
	}

	@Override
	@Transactional
	public AuthResponse authenticate(AuthRequest authRequest) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					authRequest.getUsername(), authRequest.getPassword());

			authenticationProvider.authenticate(authenticationToken);

			User currentUser = userRepository.findByUsername(authRequest.getUsername())
					.orElseThrow(() -> new BaseException(new ErrorMessage(ErrorType.USERNAME_NOT_FOUND,
							"Username '" + authRequest.getUsername() + "' is not found.")));

			

			try {
				refreshTokenRepository.deleteByUser(currentUser);
			} catch (Exception e) {
				throw new BaseException(new ErrorMessage(ErrorType.REFRESH_TOKEN_DELETE_FAILED, e.getMessage()));
			}
			
			String accessToken = jwtService.generateToken(currentUser);

			RefreshToken refreshToken = createRefreshToken(currentUser);
			refreshTokenRepository.save(refreshToken);



			return new AuthResponse(accessToken, refreshToken.getRefreshToken());

		} catch (AuthenticationException e) {
			throw new BaseException(new ErrorMessage(ErrorType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}

	}

	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}

	@Override
	@Transactional
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

		RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken())
				.orElseThrow(() -> new BaseException(
						new ErrorMessage(ErrorType.REFRESH_TOKEN_IS_NOT_FOUND, "Refresh token  is not found.")));

		if (!isValidRefreshToken(refreshToken.getExpiredDate())) {
			throw new BaseException(
					new ErrorMessage(ErrorType.REFRESH_TOKEN_IS_ALREADY_EXPIRED, "Refresh token is already expired"));

		}
		
		if (refreshToken.isUsed()) {
	        throw new BaseException(new ErrorMessage(ErrorType.REFRESH_TOKEN_ALREADY_USED, "Refresh token is already used."));
	    }
		
		refreshToken.setUsed(true);
		refreshTokenRepository.save(refreshToken);
		
		User currentUser = refreshToken.getUser();
		
		
		String newAccessToken = jwtService.generateToken(currentUser);

		
	    RefreshToken newRefreshToken = createRefreshToken(currentUser);
	    refreshTokenRepository.save(newRefreshToken);

		
		
		
		return new AuthResponse(newAccessToken, newRefreshToken.getRefreshToken());
	}

}
