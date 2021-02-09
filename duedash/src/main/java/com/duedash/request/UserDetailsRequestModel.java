package com.duedash.request;

import javax.persistence.Entity;

import lombok.Data;


@Data
public class UserDetailsRequestModel {// for getting request  in json format
	// convert to java object 
	private String fullname; 
	private String email;
	private String password;
	
	
	

	

}
