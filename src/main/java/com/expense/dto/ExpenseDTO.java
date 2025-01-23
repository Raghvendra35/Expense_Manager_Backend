package com.expense.dto;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDTO {

	private String expenseId;
	private String name;
	private String note;
	private String category;
	private Date date;
	private BigDecimal amount;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
