package com.springboot.user.service;

import com.springboot.user.exception.UserNotFoundException;
import com.springboot.user.model.User;

public interface LoginService {
	
	public String createUser(User user) throws Exception;
	public String validateUser(User user) throws Exception ;
	public String updateUser(User user) throws UserNotFoundException, Exception;
	public String deleteUser(String email) throws Exception;

}
