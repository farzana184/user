package com.springboot.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.user.exception.UserNotFoundException;
import com.springboot.user.model.User;
import com.springboot.user.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="/login")
//Api() is a swagger annotation to described controller class
@Api(value = "LoginControllerImpl", description = "Login Controller : to perform CRUD operation on user data")
public class LoginControllerImpl implements LoginController{
	
	@Autowired
	private LoginService loginService;
	
	Logger logger  = LoggerFactory.getLogger(LoginControllerImpl.class);
	
	@GetMapping("/validateUser")
	@ApiOperation(value = "Validate user email & password for login", response = String.class)
	public String validateUser(@RequestBody User user) throws Exception {
		
		logger.info("LoginControllerImpl:validateUser validate user");
		return loginService.validateUser(user);
	}
	
	@PostMapping("/createUser")
	@ApiOperation(value = "Cretae user", response = String.class)
	public String createUser(@RequestBody User user) throws Exception {
		logger.info("LoginControllerImpl:validateUser create user");
		return loginService.createUser(user);
	}
	
	@PutMapping("/updateUser")
	@ApiOperation(value = "Update existing user data", response = String.class)
	public String updateUser(@RequestBody User user) throws Exception {
		logger.info("LoginControllerImpl:updateUser update user");
		return loginService.updateUser(user);
	}
	
	@DeleteMapping("/deleteUser/{email}")
	@ApiOperation(value = "Delete existing user data", response = String.class)
	public String deleteUser(@PathVariable String email) throws Exception {
		logger.info("LoginControllerImpl:deleteUser delete user email: " +email );
		return loginService.deleteUser(email);
	}

}
