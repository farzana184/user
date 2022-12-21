package com.springboot.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerExceptionHandler {
	   @ExceptionHandler(value = UserNotFoundException.class)
	   public ResponseEntity<Object> exception(UserNotFoundException exception) {
	      return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(value = Exception.class)
	   public ResponseEntity<Object> exception(Exception exception) {
	      return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	   }

}
