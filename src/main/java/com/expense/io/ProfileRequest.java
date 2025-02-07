package com.expense.io;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileRequest
{
    @NotBlank(message ="Name is required")
    @Size(min=2, message="Name should be min 2 character")
	private String name;
    
    @NotBlank(message ="Email is required")
    @Email(message="Provide the valid email address")
	private String email;

    @NotBlank(message ="Password is required")
    @Size(message ="Password should be atleast 5 characters")
	private String password;
}
