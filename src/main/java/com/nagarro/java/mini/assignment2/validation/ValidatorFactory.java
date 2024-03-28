package com.nagarro.java.mini.assignment2.validation;

import com.nagarro.java.mini.assignment2.exception.MismatchException;

public class ValidatorFactory {
	private static final ValidatorFactory instance=new ValidatorFactory();
	
	public static Validator getValidator(String input) {
        if ("Numeric".equals(input)) {
            return new NumericValidator();
        } else if ("Alphabetic".equals(input)) {
            return new EnglishAlphabetsValidator();
        } else {
            throw new MismatchException("Invalid input type");
        }
    }
	public static ValidatorFactory getInstance() {
		return instance;
	}

}
