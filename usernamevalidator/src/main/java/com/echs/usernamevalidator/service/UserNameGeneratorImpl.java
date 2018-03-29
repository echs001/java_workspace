package com.echs.usernamevalidator.service;

import java.util.Random;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserNameGeneratorImpl implements UserNameGeneratorI{

	@Autowired
	@Qualifier(value="userNameValidatorImpl")
	UserNameValidatorI userNameValidator;
	@Autowired
	private PropertiesAccessService propertiesAccessService;	
	
	@Override
	public TreeSet<String> generateRandomUserNames(String baseName) {
		
		int numberOfExtraChars = propertiesAccessService.getNumberOfExtraCharsForNameGeneration();
		int numberOfNamesToGenerate = propertiesAccessService.getNumberOfWordsGenerated();

		Random rand = new Random();
		char generatedChar = ' ';
		String newName = "";
		TreeSet<String> listNewNames = new TreeSet<>();
		
		while (numberOfNamesToGenerate > 0)
		{
			newName = baseName;
			for (int j = 0; j < numberOfExtraChars; j++)
			{
				//1- Random from 1 to 2 to choose if next char is going to be number or letter
				//1 = number, 2 = letter
				int charType = rand.nextInt(2) + 1;
				if (charType == 1)
				{
					generatedChar = (char) (rand.nextInt(10) + 48);
				}
				else
				{
					generatedChar = (char) (rand.nextInt(26) + 97);
				}
				newName+=generatedChar;									
			}
			if (!userNameValidator.userExistsInDB(newName))
			{
				listNewNames.add(newName);
				numberOfNamesToGenerate--;
			}
		}
		return listNewNames;
	}

}
