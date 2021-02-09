package com.duedash.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.duedash.service.UserService;

import com.duedash.share.dto.UserDto;



@RestController
@RequestMapping("/users")//http:localhost:8090/users
public class LoginApi {
	@Autowired
UserService userService;
	
@PostMapping
	public com.duedash.response.UserRest createUser(com.duedash.request.UserDetailsRequestModel userDetils)
	{
	com.duedash.response.UserRest returnValue=new com.duedash.response.UserRest();
	UserDto userDto=new UserDto();
	BeanUtils.copyProperties(userDetils, userDto);
	UserDto createdUser=userService.createUser(userDto);
	BeanUtils.copyProperties(createdUser, returnValue);
	return returnValue;
			}
}
