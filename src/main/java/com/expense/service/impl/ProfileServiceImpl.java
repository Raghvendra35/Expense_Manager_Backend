package com.expense.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.dto.ProfileDTO;
import com.expense.entity.ProfileEntity;
import com.expense.exceptions.ItemExistsException;
import com.expense.repository.ProfileRepository;
import com.expense.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
	
	
	private final ProfileRepository profileRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder encoder;

	
	
	
	/**
	 * It will save the user details to database
	 * @param profileDTO
	 * @return profileDTO
	 * **/
	@Override
	public ProfileDTO createProfile(ProfileDTO profileDTO) {
		
		//Check Email Exists or not
		if(profileRepository.existsByEmail(profileDTO.getEmail()))
		{
			throw new ItemExistsException("Profile alreadt exists "+ profileDTO.getEmail());
		}
		
		
		//Encrypter Password
		profileDTO.setPassword(encoder.encode(profileDTO.getPassword()));
		
		ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
		profileEntity.setProfileId(UUID.randomUUID().toString());
		profileEntity = profileRepository.save(profileEntity);
		log.info("Printing the profile entity details {}", profileEntity);
		return mapToProfileDTO(profileEntity);
	}

    /**
	 * Mapper method to map values from profileEntity to profileDTO
	 * @param profileEntity
	 * @return profileDTO
	 * **/
	private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
		return modelMapper.map(profileEntity, ProfileDTO.class);
	}

	/**
	 * Mapper method to map values from profileDTO to profileEntity
	 * @param profileEntity
	 * @return profileDTO
	 * **/
	private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
		return modelMapper.map(profileDTO, ProfileEntity.class);
	}

	
	

}
