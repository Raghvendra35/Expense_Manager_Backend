package com.expense.service;

import com.expense.dto.ProfileDTO;

public interface ProfileService {

	/**
	 * API endpoint to register new user
	 * @param profileRequest
	 * @return profileResponse
	 * **/
	public ProfileDTO createProfile(ProfileDTO profileDTO);
}
