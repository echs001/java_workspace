package com.echs.usernamevalidator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PropertiesAccessService {

	@Autowired 
	private Environment env;

	public int getNumberOfExtraCharsForNameGeneration()
	{
		return Integer.valueOf(env.getProperty("const.numbersOfExtraChars"));
	}

	public int getNumberOfWordsGenerated()
	{
		return Integer.valueOf(env.getProperty("const.numbersOfWordsGenerated"));
	}
	
	public String getErrorMessage(String key)
	{
		return env.getProperty("error.message." + key);
	}
	public String getErrorCode(String key)
	{
		return env.getProperty("error.code." + key);
	}
	

	
}
