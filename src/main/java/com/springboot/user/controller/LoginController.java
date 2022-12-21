package com.springboot.user.controller;

import com.springboot.user.exception.UserNotFoundException;
import com.springboot.user.model.User;

public interface LoginController {
	
	public String createUser(User user) throws Exception;
	public String validateUser(User user) throws Exception ;
	public String updateUser(User user) throws UserNotFoundException, Exception;
	public String deleteUser(String email) throws Exception;

}
