package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long>
{

}
