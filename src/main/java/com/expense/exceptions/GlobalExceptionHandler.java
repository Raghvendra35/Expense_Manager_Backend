package com.expense.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.expense.io.ErrorObject;

import lombok.extern.slf4j.Slf4j;

/**
 *Global Exception Handler for all the exceptions
 *@author Raghvendra
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorObject handleResourceNotFoundExcetion(ResourceNotFoundException ex,WebRequest request)//WebRequest to provide the path
	{
		log.error("Throwing resource not found Exception from global Exception handler {}", ex.getMessage());
		return ErrorObject.builder()
		           .errorCode("DATA_NOT_FOUND")
		           .statusCode(HttpStatus.NOT_FOUND.value())
		           .message(ex.getMessage())
		           .timestamp(new Date())
		           .build();
	}
	
	
	
	//This method when we save data then we pass the message that field is required
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			                                                   HttpHeaders headers, HttpStatusCode status,
			                                                   WebRequest request)
	{
		Map<String,Object> errorResponse=new HashMap<>();
		List<String> errors=ex.getBindingResult().getFieldErrors().stream().map(field -> field.getDefaultMessage()).collect(Collectors.toList());
		
		errorResponse.put("statusCode", HttpStatus.BAD_REQUEST.value());
		errorResponse.put("message", errors);
		errorResponse.put("timestamp", new Date());
		errorResponse.put("errorCode", "VALIDATION_FAILED");
        
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorObject handleGeneralExcetion(Exception ex,WebRequest request)//WebRequest to provide the path
	{
		log.error("Throwing Exception from global Exception handler {}", ex.getMessage());
		return ErrorObject.builder()
		           .errorCode("UNEXPECTED_ERROR")
		           .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
		           .message(ex.getMessage())
		           .timestamp(new Date())
		           .build();
	}
	
	//This method will run when emailId already exists in db and u are creating new registration with exists mail then it give error CONFLICT
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ItemExistsException.class)
	public ErrorObject handleItemExistsException(ItemExistsException ex, WebRequest request)
	{
		log.error("Throwing the ItemExistsException from GlobalExceptionHandler {}", ex.getMessage());
		return ErrorObject.builder()
				          .errorCode("DATA_EXISTS")
				          .statusCode(HttpStatus.CONFLICT.value())
				          .message(ex.getMessage())
				          .timestamp(new Date())
				          .build();
	}
	
	
}














