package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findById(Integer userId);

	List<User> findByNameEqualsIgnoreCaseOrPinCodeEqualsOrSurNameEqualsIgnoreCase(String name, Integer pinCode,String surName);
	
	@Query(value = "select * from user_master order by dob,joining_date asc", nativeQuery = true)
	List<User> sortByDobAndJoiningDate();
	
}