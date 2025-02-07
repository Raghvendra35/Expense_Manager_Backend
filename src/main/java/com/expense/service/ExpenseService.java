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
	public List<ExpenseDTO> getAllExpense();
	
	/**
	 *It will fetch the single expense from database
	 *@param expenseId
	 * @return ExpenseDTO
	 */
	public ExpenseDTO getExpenseById(String expenseId);
	
	/**
	 *It will fetch the delete expense from database
	 *@param expenseId
	 * @return void
	 */
	public void deleteExpenseByExcpenseId(String excpenseId);
	
	public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO);
	
	public ExpenseDTO updateExpenseDetails(ExpenseDTO expenseDTO,String expenseId);
	
	
}
