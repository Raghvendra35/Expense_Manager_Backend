package com.expense.io;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorObject {

	private Integer statusCode;
	private String message;
	private Date  timestamp;
	private String errorCode;
	
}
