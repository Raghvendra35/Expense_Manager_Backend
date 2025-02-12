package com.expense.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expense.entity.ProfileEntity;
import com.expense.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

	
	private final ProfileRepository profielRepository;
	
	public ProfileEntity getLoggedInProfile()
	{
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String email=authentication.getName();
		return profielRepository.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("Profile not found for the email "+ email));
	}
	
	
	
}
