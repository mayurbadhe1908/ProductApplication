package com.product.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.backend.entity.User;
import com.product.backend.service.MyUserDetailsService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private MyUserDetailsService service;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		
		return service.createUser(user);
		
	}
	
}
