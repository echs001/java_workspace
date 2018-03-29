package com.echs.usernamevalidator.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.echs.usernamevalidator.entity.RestrictedWord;
import com.echs.usernamevalidator.entity.User;

public interface RestrictedWordRepository extends JpaRepository<RestrictedWord, Long>{
	
	List<RestrictedWord> findByName(@Param("name") String name);
}
