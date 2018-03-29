package com.echs.usernamevalidator.service;

import java.util.TreeSet;

public interface UserNameGeneratorI {

	public TreeSet<String> generateRandomUserNames(String baseName);
}
