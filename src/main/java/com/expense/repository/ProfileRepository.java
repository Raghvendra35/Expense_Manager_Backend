package com.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>
{

	Optional<ProfileEntity> findByEmail(String emailId);
	
	public boolean existsByEmail(String emailId);

	
}
