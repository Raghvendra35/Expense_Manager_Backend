package com.expense.io;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse {

	private String expenseId;
	private String name;
	private String note;
	private String category;
	private Date date;
	private BigDecimal amount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
