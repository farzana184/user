package com.springboot.user.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@ApiModel() is used for swagger to described class usage
@ApiModel(value="User",description="Model class for to hold user information")
public class User {
	
	//@ApiModelProperty() is used for swagger to described attribute
	@ApiModelProperty(notes = "email", example = "abc@gmail", required = true, position = 0)
	private String email;
	@ApiModelProperty(notes = "lastName", example = "abc", required = true, position = 1)
	private String lastName;
	@ApiModelProperty(notes = "firstName", example = "john", required = true, position = 2)
	private String firstName;
	@ApiModelProperty(notes = "userPassword", example = "abc234", required = true, position = 3)
	private String userPassword;
	@ApiModelProperty(notes = "phoneNumber", example = "122423122", required = true, position = 4)
	private String phoneNumber;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
