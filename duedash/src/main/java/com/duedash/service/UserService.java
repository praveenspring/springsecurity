package com.duedash.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.duedash.share.dto.UserDto;

public interface UserService extends UserDetailsService {

	 UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);

}
