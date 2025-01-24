package com.expense.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.expense.io.ErrorObject;

import lombok.extern.slf4j.Slf4j;

/**
 *Global Exception Handler for all the exceptions
 *@author Raghvendra
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler
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
}
