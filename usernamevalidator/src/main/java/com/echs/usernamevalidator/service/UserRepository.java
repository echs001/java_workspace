package com.echs.usernamevalidator.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.echs.usernamevalidator.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByName(@Param("name") String name);
}
