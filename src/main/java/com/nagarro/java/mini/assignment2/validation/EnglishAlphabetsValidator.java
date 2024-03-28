package com.nagarro.java.mini.assignment2.validation;
import java.util.*;

import com.nagarro.java.mini.assignment2.exception.MismatchException;

public class EnglishAlphabetsValidator implements Validator {
    private static final EnglishAlphabetsValidator instance = new EnglishAlphabetsValidator();

    private static final Set<String> ALLOWED_VALUES = new HashSet<>(Arrays.asList("Name","name","age","Age", "even","Even","Odd", "odd","sortType","sortOrder","limit","offset"));
    EnglishAlphabetsValidator() {}

    public static EnglishAlphabetsValidator getInstance() {
        return instance;
    }
    

    @Override
    public void validate(String input) throws MismatchException {

    	
    	if (!ALLOWED_VALUES.contains(input)) {
            throw new MismatchException ("Invalid value for sortType/sortOrder: " + input);
        }
    }
}

