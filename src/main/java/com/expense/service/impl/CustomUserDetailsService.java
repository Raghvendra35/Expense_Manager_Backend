package com.expense.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expense.entity.ProfileEntity;
import com.expense.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
	
	private final ProfileRepository profileRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ProfileEntity profileEntity=profileRepository.findByEmail(username)
				                                     .orElseThrow(() -> new UsernameNotFoundException("Profile not found for the email " + username));
		log.info("Inside loadUserByUsername()::: printing the profile details {} "+ profileEntity);
	    return new User(profileEntity.getEmail(),profileEntity.getPassword(),new ArrayList<>());
	}

}
