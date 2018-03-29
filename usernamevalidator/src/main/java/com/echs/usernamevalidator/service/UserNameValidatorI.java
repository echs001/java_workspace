package com.echs.usernamevalidator.service;

import com.echs.usernamevalidator.main.Result;

	
public interface UserNameValidatorI {
	public Result validateUser(String userName);
	public boolean userExistsInDB(String userName);

}
