package com.expense.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ExpenseDTO;
import com.expense.io.ExpenseResponse;
import com.expense.service.ExpenseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Add Java Doc
/*
 *This is controller class for Expense Module
 *@author Raghvendra 
 * */
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExpenseController 
{

	private final ExpenseService expenseService;
	private final ModelMapper modelMapper;
	
	/**
	 * It will fetch the expense from database
	 * @return list
	 * **/
	@GetMapping("expenses")
	public List<ExpenseResponse> getExpense()
	{
		log.info("API GET /expenses called");
		List<ExpenseDTO> list=expenseService.getAllExpense();
		log.info("Printing the data from service {} ", list);
		//Convert the Expense DTO to Expense Response
	    List<ExpenseResponse> response=list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO)).collect(Collectors.toList());
		return response;
	}

	
	/**
	 * Mapper method for converting expense DTO object to expense response
	 * @param expenseDTO
	 * @return ExpenseResponse
	 */
	private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO) 
	{
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
	}
	
	
	
	/**
	 * It will fetch the single expense from database
	 * @param expenseId
	 * @return expenseResponse
	 * **/
	@GetMapping("/expenses/{expenseId}")
	public ExpenseResponse geyExpenseById(@PathVariable String expenseId)
	{
		log.info("API GET /expenses/{} called ",expenseId);
		ExpenseDTO expenseDTO=expenseService.getExpenseById(expenseId);
		log.info("printing the expense details {}", expenseDTO);
		return mapToExpenseResponse(expenseDTO);
	}
	 
	/**
	 * It will the delete expense from database
	 * @param expenseId
	 * @return void
	 * **/
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/expenses/{expenseId}")
	public void deleteExcepenseId(@PathVariable String expenseId)
	{ 
		log.info("API DELETE /expenses/{} called ", expenseId);
		expenseService.deleteExpenseByExcpenseId(expenseId);
	}
	
}





























