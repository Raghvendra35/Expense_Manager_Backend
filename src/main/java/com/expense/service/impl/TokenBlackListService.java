package com.expense.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {

	private Set<String> blacklist=new HashSet<>();
	
	public void addTokenToBlackList(String token) {
		blacklist.add(token);
	}
	
	public boolean isTokenBlacklisted(String token)
	{
		return blacklist.contains(token);
	}
	
}
