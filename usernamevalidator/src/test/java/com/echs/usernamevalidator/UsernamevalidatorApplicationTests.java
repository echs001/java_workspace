package com.echs.usernamevalidator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.echs.usernamevalidator.entity.User;
import com.echs.usernamevalidator.main.Result;
import com.echs.usernamevalidator.service.UserNameValidatorI;
import com.echs.usernamevalidator.service.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UsernamevalidatorApplicationTests {

	@Autowired
	@Qualifier(value="userNameValidatorImpl")
	private UserNameValidatorI userNameValidator;
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void userNameValidation() {
		
		//Empty name (lower than 6 chars)
		String name = "";
		Result validatedUser = userNameValidator.validateUser(name);
		assertEquals("E003",validatedUser.getErrorCode());

		//Name contains restricted words (enough base name to suggest list of names)
		name = "Michaelcrack001";
		validatedUser = userNameValidator.validateUser(name);
		assertEquals("E004",validatedUser.getErrorCode());

		//Name contains restricted words (not enough base name to suggest list of names)
		name = "Miccrack01";
		validatedUser = userNameValidator.validateUser(name);
		assertEquals("E002",validatedUser.getErrorCode());


	}
	@Test
	public void userDBInsertion() 
	{
		//Insert user (DB is clean, id must be generated to be 1)
		String name = "echs001";
		User user = new User(name);
		userRepository.save(user);
		assertEquals(1, user.getId());
		
		//Duplicate name. Should throw an error code E001
		Result validatedUser = userNameValidator.validateUser(name);
		validatedUser = userNameValidator.validateUser(name);
		assertEquals("E001",validatedUser.getErrorCode());

		//Insert another user, id should be 2
		name = "amazingUser";
		user = new User(name);
		userRepository.save(user);
		assertEquals(2, user.getId());
	}
	

}
