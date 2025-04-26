package com.alirizakaygusuz.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.dto.AuthRequest;
import com.alirizakaygusuz.dto.DtoUser;
import com.alirizakaygusuz.entity.User;
import com.alirizakaygusuz.mapper.UserMapper;
import com.alirizakaygusuz.repository.UserRepository;
import com.alirizakaygusuz.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
	
	
	@Autowired
	private  UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	private User createUser(AuthRequest authRequest) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setUsername(authRequest.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
		
		return user;
	}
	
	  
	
	@Override
	public DtoUser registerUser(AuthRequest authRequest) {
		
		User savedUser = userRepository.save(createUser(authRequest));
		

		
		return userMapper.userToUserDto(savedUser);
	}
	
	
	

	
}
