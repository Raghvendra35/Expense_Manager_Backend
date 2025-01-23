package com.expense.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.expense.dto.ExpenseDTO;
import com.expense.entity.ExpenseEntity;
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
}


























