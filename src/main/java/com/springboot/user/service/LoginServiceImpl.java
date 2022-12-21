package com.springboot.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.user.dao.LoginDAO;
import com.springboot.user.model.User;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDAO loginDAO;
	
	Logger logger  = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Override
	public String validateUser(User user) throws Exception {
		logger.info("LoginServiceImpl:validateUser : " );
		return loginDAO.validateUser(user);
	}

	@Override
	public String createUser(User user) throws Exception {
		logger.info("LoginServiceImpl:createUser : " );
		return loginDAO.createUser(user);
	}

	@Override
	public String updateUser(User user) throws Exception {
		logger.info("LoginServiceImpl:updateUser : " );
		return loginDAO.updateUser(user);
	}

	@Override
	public String deleteUser(String email) throws Exception {
		logger.info("LoginServiceImpl:deleteUser : " );
		return loginDAO.deleteUser(email);
	}
	

}
