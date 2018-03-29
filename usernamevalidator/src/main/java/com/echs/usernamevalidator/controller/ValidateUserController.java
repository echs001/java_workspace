package com.echs.usernamevalidator.controller;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.echs.usernamevalidator.entity.User;
import com.echs.usernamevalidator.main.Result;
import com.echs.usernamevalidator.service.UserNameValidatorI;
import com.echs.usernamevalidator.service.UserRepository;

@Controller
public class ValidateUserController {
	
	@Autowired
	@Qualifier(value="userNameValidatorImpl")
	private UserNameValidatorI userNameValidator;
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(value="/validate-user", method = RequestMethod.GET)	
	public String showValidateUserPage(ModelMap model)
	{
		return "validateUser";
	}

	@RequestMapping(value="/validate-user", method = RequestMethod.POST)	
	public String showValidateUserPage(ModelMap model, @RequestParam String name)
	{
		
		User user;
		TreeSet<String> suggestedUserNameList;
		Result validatedUser = userNameValidator.validateUser(name);
		if(!validatedUser.isValid())
		{
			model.put("errorMessage", validatedUser.getErrorMessage());
			suggestedUserNameList = validatedUser.getValidUserNames();
			if(suggestedUserNameList != null && suggestedUserNameList.size() > 0)
			{
				model.put("suggestedNamesList", suggestedUserNameList);
			}
		}
		else
		{
			user = new User(name);
			userRepository.save(user);
			model.put("nameAccepted", "NAME ACCEPTED AND INSERTED WITH THE ID: " + user.getId());
		}		
		return "validateUser";
	}
}
