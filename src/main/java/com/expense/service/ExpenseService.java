package com.expense.service;

import java.util.List;

import com.expense.dto.ExpenseDTO;

/**
 * Service interface for expense module
 * @author Raghvendra 
  */
public interface ExpenseService {

	/**
	 *It will fetch the expense from database
	 * @return list
	 */
	List<ExpenseDTO> getAllExpense();
}
