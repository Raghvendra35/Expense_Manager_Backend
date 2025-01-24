package com.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.entity.ExpenseEntity;

/**
 *JPA Repository for expense resource
 *@author Raghvendra
 */
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long>
{
	/**
	 *It will fetch the single ExpenseEntity from database
	 *@param expenseId
	 * @return Optional ExpenseEntity
	 */
    //we use Optional to handle the null pointer Exception
	public Optional<ExpenseEntity> findByExpenseId(String expenseId);
}
