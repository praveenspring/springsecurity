package com.duedash.controller;

import java.beans.Beans;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duedash.response.UserRest;
import com.duedash.service.UserService;

import com.duedash.share.dto.UserDto;



@RestController
@RequestMapping("/users")//http:localhost:8090/users
public class LoginApi {
	@Autowired
UserService userService;
	@GetMapping(path="{/id}")
	public UserRest getUser(@PathVariable String id ) {
		
		UserRest returnValue=new UserRest();
		UserDto userDto=userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		 return returnValue;
	}
	
@PostMapping
public com.duedash.response.UserRest createUser(@RequestBody com.duedash.request.UserDetailsRequestModel userDetils)
	{
	com.duedash.response.UserRest returnValue=new com.duedash.response.UserRest();
	UserDto userDto=new UserDto();
	BeanUtils.copyProperties(userDetils, userDto);
	UserDto createdUser=userService.createUser(userDto);
	BeanUtils.copyProperties(createdUser, returnValue);
	return returnValue;
			}
}
