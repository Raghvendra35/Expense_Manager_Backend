package com.expense.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}





























