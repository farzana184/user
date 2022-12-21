package com.springboot.user.dao;

import com.springboot.user.exception.UserNotFoundException;
import com.springboot.user.model.User;

public interface LoginDAO {
	public String createUser(User user) throws Exception;
	public String validateUser(User user) throws Exception ;
	public String updateUser(User user) throws UserNotFoundException, Exception;
	public String deleteUser(String email) throws Exception;

}
