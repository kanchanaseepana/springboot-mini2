package com.nagarro.java.mini.assignment2.validation;

import com.nagarro.java.mini.assignment2.exception.MismatchException;

public interface Validator {
	
	 void validate(String input)throws MismatchException;

}
