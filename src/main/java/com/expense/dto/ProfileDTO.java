package com.expense.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.expense.io.ProfileResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileDTO {
	
	private String profileId;
	private String email;
	private String name;
	private String password;
    private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
