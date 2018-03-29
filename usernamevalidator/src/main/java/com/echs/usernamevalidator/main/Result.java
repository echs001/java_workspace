package com.echs.usernamevalidator.main;

import java.util.TreeSet;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Result {
	
	private boolean valid;
	private TreeSet<String> validUserNames;
	private String errorCode;
	private String errorMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public TreeSet<String> getValidUserNames() {
		return validUserNames;
	}
	public void setValidUserNames(TreeSet<String> validUserNames) {
		this.validUserNames = validUserNames;
	}

}
