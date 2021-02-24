package com.jb.registration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidCipRequestException(UserAlreadyExistsException ex,
			WebRequest request) {
		ErrorResponse exceptionResponse = new ErrorResponse();
		log.error("Exception found: ", ex);
		exceptionResponse.setCode(ErrorCode.USER_ALREADY_EXISTS.toString());
		exceptionResponse.setDescription("A user with the given username already exists");
		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
	}
	

	 @ExceptionHandler(Exception.class)
	 public final ResponseEntity<ErrorResponse> handleExceptionGenerally(Exception ex, 
			 WebRequest request) throws Exception {
		 	ErrorResponse exceptionResponse = new ErrorResponse();
			log.error("Exception found: ", ex);
			exceptionResponse.setCode(ErrorCode.USER_REGISTRATION_REQUEST_FAILED.toString());
			exceptionResponse.setDescription("An error occoured in the server, which prevented user creation.");
		  return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			ErrorResponse exceptionResponse = new ErrorResponse();
			exceptionResponse.setCode(ErrorCode.INVALID_INPUT.toString());
			exceptionResponse.setDescription("Invalid input data");
			return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
		}
}
