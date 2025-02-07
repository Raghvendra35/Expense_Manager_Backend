package com.expense.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ProfileDTO;
import com.expense.io.ProfileRequest;
import com.expense.io.ProfileResponse;
import com.expense.service.ProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

	
	private final ModelMapper modelMapper;
	private final ProfileService profileService;
	
	
	/**
	 * API endpoint to register new user
	 * @param profileRequest
	 * @return profileResponse
	 * **/
    @ResponseStatus(HttpStatus.CREATED)	
	@PostMapping("/register")
	public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest)
	{
		log.info("API /register is called {}", profileRequest);
		ProfileDTO profileDTO=mapToProfileDTO(profileRequest);
		profileDTO=profileService.createProfile(profileDTO);
	    log.info("Printing the profile dto details {}", profileDTO);
		return mapToProfileResponse(profileDTO);
	}


    
    /**
	 * Mapper method to map values from profileDTO to profileResponse
	 * @param profileDTO
	 * @return profileResponse
	 * **/
	private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
		return modelMapper.map(profileDTO, ProfileResponse.class);
	}

	/**
	 * Mapper method to map values from profileRequest to profileDTO
	 * @param profileRequest
	 * @return profileDTO
	 * **/
	private ProfileDTO mapToProfileDTO(@Valid ProfileRequest profileRequest) {
		
		return modelMapper.map(profileRequest, ProfileDTO.class);
	}



}
