package com.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>
{

}
