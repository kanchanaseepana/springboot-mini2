package com.nagarro.java.mini.assignment2.validation;

import com.nagarro.java.mini.assignment2.exception.MismatchException;

public class NumericValidator implements Validator {
    private static final NumericValidator instance = new NumericValidator();

    NumericValidator() {}

    public static NumericValidator getInstance() {
        return instance;
    }

    @Override
    public void validate(String input) {
        
        try {
            int limit=Integer.parseInt(input);
           if(limit<0 || limit>5) {
        	   throw new MismatchException("Invalid");
           }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());        }
    }
}
