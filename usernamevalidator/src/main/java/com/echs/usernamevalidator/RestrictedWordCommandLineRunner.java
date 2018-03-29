package com.echs.usernamevalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.echs.usernamevalidator.entity.RestrictedWord;
import com.echs.usernamevalidator.service.RestrictedWordRepository;

@Component
public class RestrictedWordCommandLineRunner implements CommandLineRunner{

	@Autowired
	private RestrictedWordRepository restrictedWordRepository;
	
	String[] restrictedWord = {"cannabis", 
							   "abuse", 
							   "crack", 
							   "damn", 
							   "drunk", 
							   "grass"};
	
	@Override
	public void run(String... args) throws Exception {
		for (String word : restrictedWord) {
			RestrictedWord restrictedWord = new RestrictedWord(word);
			restrictedWordRepository.save(restrictedWord);
			
		}
	}

}
