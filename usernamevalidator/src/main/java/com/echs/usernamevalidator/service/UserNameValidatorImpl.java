package com.echs.usernamevalidator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.echs.usernamevalidator.entity.RestrictedWord;
import com.echs.usernamevalidator.entity.User;
import com.echs.usernamevalidator.main.Result;

@Component
public class UserNameValidatorImpl implements UserNameValidatorI {

	@Autowired 
	private Environment env;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestrictedWordRepository restrictedWordRepository;
	@Autowired
	@Qualifier(value="userNameGeneratorImpl")
	private UserNameGeneratorI userNameGenerator;
	
	@Autowired
	private PropertiesAccessService propertiesAccessService;
	
	@Override
	public Result validateUser(String userName) {

		Result result = new Result();
		if (!isValidLength(userName))
		{
			result.setValid(false);
			result.setErrorMessage(propertiesAccessService.getErrorMessage("invalidUserNameLength"));
			result.setErrorCode(propertiesAccessService.getErrorCode("invalidUserNameLength"));
			
		}
		else if (userExistsInDB(userName))
		{
			result.setValidUserNames(userNameGenerator.generateRandomUserNames(userName));
			result.setValid(false);
			result.setErrorMessage(propertiesAccessService.getErrorMessage("duplicatedName"));
			result.setErrorCode(propertiesAccessService.getErrorCode("duplicatedName"));
		}
		else 
		{
			List<RestrictedWord> restrictedWordsInUserNameList = getRestrictedWordsInUserName(userName);
			if (restrictedWordsInUserNameList.size() > 0)
			{
				String userNameWithoutRestrictedWords = userName;
				result.setValid(false);
				//Two options here:
				//1- If the userName without the restricted words has a length greater than 5, we're going to suggest a list of names
				//2- If the userName without the restricted words has a length lower than 6, we're going to show error message of restricted word
				
				//Remove all restricted words from base name
				for (RestrictedWord restrictedWord : restrictedWordsInUserNameList) 
				{
					userNameWithoutRestrictedWords = userNameWithoutRestrictedWords.replace(restrictedWord.getName(), "");
				}				
				//If the resulting user name has a length greater than 5 then we're going to suggest a list of valid user names
				if (userNameWithoutRestrictedWords.length() > 5)
				{
					result.setValidUserNames(userNameGenerator.generateRandomUserNames(userNameWithoutRestrictedWords));
					result.setErrorMessage(propertiesAccessService.getErrorMessage("containsRestrictedWordUseSuggestedList"));					
					result.setErrorCode(propertiesAccessService.getErrorCode("containsRestrictedWordUseSuggestedList"));
				}
				//If the resulting user name has a length lower than 6 we're going to show error message - restricted word
				else
				{
					result.setErrorMessage(propertiesAccessService.getErrorMessage("containsRestrictedWord"));
					result.setErrorCode(propertiesAccessService.getErrorCode("containsRestrictedWord"));
				}
				
			}
			else
			{
				result.setValid(true);
			}
			
		}
		return result;
	}
	
	private boolean isValidLength(String userName) 
	{
		boolean result = true;
		if (userName.length() < 6)
		{
			result = false;
		}
		return result;
	}

	@Override
	public boolean userExistsInDB(String userName)
	{
		boolean exists = false;
		List<User> userList = userRepository.findByName(userName);
		if (userList.size() > 0)
		{
			exists = true;
		}
		
		return exists;
	}
	
	public List<RestrictedWord> getRestrictedWordsInUserName(String userName)
	{
		List<RestrictedWord> restrictedWordList = restrictedWordRepository.findByName(userName);
		return restrictedWordList;
		
	}
	
}
