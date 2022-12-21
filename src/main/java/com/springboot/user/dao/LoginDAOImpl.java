package com.springboot.user.dao;

import java.sql.ResultSet;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.springboot.user.exception.UserException;
import com.springboot.user.exception.UserNotFoundException;
import com.springboot.user.model.User;
import com.springboot.user.model.UserRowMapper;
import com.springboot.user.service.LoginServiceImpl;

@Repository
public class LoginDAOImpl implements LoginDAO{

	//ResTemplate is use fot to connect other microservices/ endpoints
	RestTemplate restTemplate = new RestTemplate();

	//JDBC Template to connect and perform CRUD operation on mySql db
	@Autowired
	JdbcTemplate jdbcTemplate;

	// to call other micro service reading from application.properties file
	//@Value("${username.baseurl}")
	//private String usernameBaseurl;

	//reading databaseName from application. properties file
	@Value("${login.table.name}")
	private String tableName;

	Logger logger  = LoggerFactory.getLogger(LoginDAOImpl.class);

	@Override
	public String validateUser(User user) throws Exception {
		try {
			logger.info("LoginDAOmpl:validateUser : " );
			String query = "select * from " + tableName + " where email=\"" + user.getEmail() + "\"";
			User retrieveUser = jdbcTemplate.queryForObject(query, new UserRowMapper());
			if (user.getUserPassword().equals(retrieveUser.getUserPassword())) {
				return "login successfull !!!!";
			}
			throw new UserException("Wrong credential, try again!");
		} catch (Exception e) {
			logger.error("Error \n"+e.getMessage() );
			throw e;
		}
	}

	@Override
	public String createUser(User user) throws Exception {
		try {
			logger.info("LoginDAOmpl:createUser : " );
			String query = "INSERT INTO " + tableName + " VALUES (?,?,?,?,?)";
			int rows = jdbcTemplate.update(query, user.getEmail(), user.getLastName(), user.getFirstName(),
					user.getUserPassword(), user.getPhoneNumber());
			/* Example code to call other microservices
			 * HttpHeaders headers = new HttpHeaders();
			  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			  HttpEntity <String> entity = new HttpEntity<String>(headers);

			  String usernameAvaibility =  restTemplate.exchange(usernameBaseurl+user.getUsername(), HttpMethod.GET, entity, String.class).getBody();
			 */
			if (rows != 0)
				return "user created succesfully!!!";
			else
				throw new UserException("user creation failed!");
		} catch (Exception e) {
			logger.error("Error \n"+e.getMessage() );
			throw e;
		}
	}

	@Override
	public String updateUser(User user) throws Exception {

		/*First check whether this user exist in the database or not */
		try {
			logger.info("LoginDAOmpl:updateUser : " );
			String query = "select * from "+tableName +" where email=\""+user.getEmail()+"\"";
			User retrieveUser = jdbcTemplate.queryForObject(query, new UserRowMapper());

			if(retrieveUser == null) {
				throw new UserNotFoundException("Custom exception");
			}

			//If user exist, then update the entire row in mySql
			query = "UPDATE "+tableName+" SET email = '"+user.getEmail()+"', lastName = '"+user.getLastName()+"', firstName = '"+user.getFirstName()+"', userPassword = '"+user.getUserPassword()+"', phoneNumber= '"+user.getPhoneNumber()+"' "
					+ "WHERE email = '"+user.getEmail()+"';";

			int countRow = jdbcTemplate.update(query);
			if(retrieveUser != null) {
				return "update successfull !!!!";
			}
			return "update failed, please try again !!!!";
		} catch (Exception e) {
			logger.error("Error \n"+e.getMessage() );
			throw e;
		}
	}

	@Override
	public String deleteUser(String email) throws Exception {
		try {
			logger.info("LoginDAOmpl:deleteUser : " );
			if (email == null) {
				throw new UserException();
			}
			String query = "delete from " + tableName + " where email='" + email + "' ;";
			int countRow = jdbcTemplate.update(query);
			if (countRow != 0) {
				return "User deleted " + email;
			} else {
				return "user deletion failed, please try again";
			} 
		} catch (Exception e) {
			logger.error("Error \n"+e.getMessage() );
			throw e;
		}
	}

}
