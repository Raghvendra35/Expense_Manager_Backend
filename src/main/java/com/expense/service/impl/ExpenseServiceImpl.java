package com.expense.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.expense.dto.ExpenseDTO;
import com.expense.entity.ExpenseEntity;
import com.expense.exceptions.ResourceNotFoundException;
import com.expense.repository.ExpenseRepository;
import com.expense.service.ExpenseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service implentation for expense module
 * @author Raghvendra 
  */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService
{

	private final ExpenseRepository expenseRepository;
	private final ModelMapper modelMapper;
	
	/**
	 *It will fetch the expense from database
	 * @return list
	 */
	@Override
	public List<ExpenseDTO> getAllExpense() 
	{
		//Call the Repository method
		List<ExpenseEntity> list=expenseRepository.findAll();
		log.info("Printing the data from repository {} ", list);

		//convert the Entity object to DTO object
		List<ExpenseDTO> listOfExpense=list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());
		
		return listOfExpense;
	}

	/**
	 *Mapper method for converting expense entity object to expense DTO
	 * @return expense DTO
	 */
	private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity)
	{
		return modelMapper.map(expenseEntity,ExpenseDTO.class);
	}

	
	
	/**
	 *It will fetch the single expense details from database
	 *@param expenseId
	 * @return ExpenseDTO
	 */
	@Override
	public ExpenseDTO getExpenseById(String expenseId)
	{ 
		//If data not found then it gives InternalServerError 500 so here we use orElseThrow Runtime Exception with the help of lambda expression
        ExpenseEntity expenseEntity=expenseRepository.findByExpenseId(expenseId)
//        		      .orElseThrow(()-> new RuntimeException("Expense not found for the ExpenseId"+  expenseId)); // this exception will throw on console not on postman/browser it throw InternalServerError 500 postman/Browser
        	          .orElseThrow(()-> new ResourceNotFoundException("Expense not found for the ExpenseId : "+  expenseId));//So Create Custom Exception to throw the Exception on browser
        log.info("Printing the expense entity details {} ", expenseEntity);
        return mapToExpenseDTO(expenseEntity);
	}

	/**
	 *It will the delete expense details from database
	 *@param expenseId
	 * @return void
	 */
	@Override
	public void deleteExpenseByExcpenseId(String excpenseId) 
	{
        log.info("Printing the expense entity details {} ", excpenseId);

		ExpenseEntity expenseEntity=expenseRepository.findByExpenseId(excpenseId)
				                   .orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
		expenseRepository.delete(expenseEntity);
	}
}


























