package com.nagarro.java.mini.assignment2.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.mini.assignment2.UserSortContext;
import com.nagarro.java.mini.assignment2.entity.*;
import com.nagarro.java.mini.assignment2.exception.MismatchException;
import com.nagarro.java.mini.assignment2.repository.UserRepository;
import com.nagarro.java.mini.assignment2.service.sorting.AgeSortingStrategy;
import com.nagarro.java.mini.assignment2.service.sorting.NameSortingStrategy;
import com.nagarro.java.mini.assignment2.validation.EnglishAlphabetsValidator;
import com.nagarro.java.mini.assignment2.validation.NumericValidator;
import com.nagarro.java.mini.assignment2.validation.Validator;
import com.nagarro.java.mini.assignment2.validation.ValidatorFactory;
import com.nagarro.java.mini.assignment2.entity.*;

@Service
public class secondApiService {
	
	
	@Autowired
	private UserSortContext userSortContext;
	@Autowired
    private RandomUserApi randomUserApi;
	
	@Autowired
	private UserRepository userRepository;
	
	
	 public finalApiResponse getSortedUsers(String sortType, String sortOrder, int limit, int offset)throws MismatchException {
	        finalApiResponse finalResponse = new finalApiResponse();


    
	        Validator numericValidator =ValidatorFactory.getInstance().getValidator("Numeric");
        	Validator alphabeticValidator=ValidatorFactory.getInstance().getValidator("Alphabetic");
	        
	        try {

	        	
	        	
	        	numericValidator.validate(Integer.toString(limit));
	        	numericValidator.validate(Integer.toString(offset));
	        	alphabeticValidator.validate(sortType);
	        	alphabeticValidator.validate(sortOrder);
	        	
	        	
	        	
	        	
	        }catch(MismatchException e) {
	        	throw e;
	        	
	        	
	        }

	        List<UserResponse> users = getUsersWithPagination(limit, offset);

	        // Set the sorting strategy based on sortType
	        if ("Name".equalsIgnoreCase(sortType)) {
	            userSortContext.setSortingStrategy(new NameSortingStrategy());
	        } else if ("Age".equalsIgnoreCase(sortType)) {
	            userSortContext.setSortingStrategy(new AgeSortingStrategy());
	        }

	        // Perform sorting based on sortOrder
	        if ("EVEN".equalsIgnoreCase(sortOrder)) {
	            userSortContext.performSort(users,"EVEN");
	        }else {
	        	userSortContext.performSort(users,"ODD");
	        	
	        }
	        
	        System.out.println(users);
	        PageInfo pagedata=new PageInfo();

	        finalResponse.setData(users);
	        finalResponse.setPageinformation(pageinformation(pagedata, limit, offset));
	        
	        
	        return finalResponse;
	    }
	 
	 
	 public List<UserResponse> getUsersWithPagination(int limit, int offset) {
	        // Validate limit and offset values
	        if (limit < 1 || limit > 5) {
	            throw new IllegalArgumentException("Invalid limit value, must be between 1 and 5");
	        }

	        if (offset < 0) {
	            throw new IllegalArgumentException("Invalid offset value, must be non-negative");
	        }

	        // Fetch data from the database using custom query
	        List<User> userList = userRepository.findUsersWithPagination(limit, offset);

	        // Convert the list of User entities to a List of UserResponse objects
	        List<UserResponse> responseList = userList.stream()
	                .map(this::toDTO)
	                .collect(Collectors.toList());

	        return responseList;
	    }


	    private UserResponse toDTO(User user) {
	        UserResponse dto = new UserResponse();
	        dto.setName(user.getName());
	        dto.setAge(user.getAge());
	        dto.setDob(user.getDob());
	        dto.setGender(user.getGender());
	        dto.setNationality(user.getNationality());
	        dto.setVerificationStatus(user.getVerificationStatus());
	        return dto;
	    }
	
	private PageInfo pageinformation(PageInfo pageinfo,int limit,int offset) {
		int count=(int) userRepository.count();
		pageinfo.setHasPreviousPage(hasPrevPage(offset));
		pageinfo.setHasNextPage(nextPage(limit,offset,count));
		pageinfo.setTotal(count);
		return pageinfo;
	}
	

    private boolean hasPrevPage(int offset) {
        return offset > 0;
    }
	
	private boolean nextPage(int limit,int offset,int totalusers) {
		return (offset+limit)<totalusers;
	}

}
