package com.expense.io;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileResponse {

	private String profileId;
	private String email;
	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
