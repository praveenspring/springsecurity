package com.duedash.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.duedash.io.entity.UserEntity;
import com.duedash.repository.UserRepository;
import com.duedash.service.UserService;
import com.duedash.share.Utils;
import com.duedash.share.dto.UserDto;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	Utils utils;
	@Autowired
	 BCryptPasswordEncoder  bCryptPasswordEncoder;
	@Override
	public UserDto createUser(UserDto user) {
		
		if(userRepo.findUserByEmail(user.getEmail()) !=null) throw new RuntimeException("Record alredy exist");
		
		UserEntity userEntity=new UserEntity();
		
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		String publicUserId=utils.generatedUserId(30);
		
		userEntity.setUserId(publicUserId);
		
		UserEntity storedUser=userRepo.save(userEntity);
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(storedUser, returnValue);
		return returnValue;
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepo.findUserByEmail(email);
		
		if(userEntity==null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>());
		
		
		
		
		
	}
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepo.findUserByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
		
	}
	

}
