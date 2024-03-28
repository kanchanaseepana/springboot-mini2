package com.nagarro.java.mini.assignment2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.mini.assignment2.entity.User;
import com.nagarro.java.mini.assignment2.entity.UserCreationRequest;
import com.nagarro.java.mini.assignment2.entity.UserResponse;
import com.nagarro.java.mini.assignment2.entity.finalApiResponse;
import com.nagarro.java.mini.assignment2.service.RandomUserApi;
import com.nagarro.java.mini.assignment2.service.secondApiService;
import com.nagarro.java.mini.assignment2.exception.*;




@RestController


public class UserController {
	
	@Autowired
    public  RandomUserApi randomUserApi;
	
	@Autowired
	public secondApiService secondApiService;

    
    

	
	
	@PostMapping("/users")
	public ResponseEntity<?> createUsers(@RequestBody UserCreationRequest size) {
	    try {
	        int requestSize = size.getSize();

	        if (requestSize > 5 || requestSize < 1) {
	            throw new MismatchException("Invalid size, size must be within 5");
	        }

	        List<UserResponse> createdUsers = randomUserApi.createUsers(requestSize);
	        return ResponseEntity.ok(createdUsers);
	    } catch (MismatchException e) {
	        // Customize the error response for invalid size
	        ErrorMessage error = new ErrorMessage(e.getMessage(), 400); // You can choose an appropriate HTTP status code
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        // Customize the error response for other exceptions
	        ErrorMessage error = new ErrorMessage("page not found, User creation failed", 404);
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	}
    

	
	@GetMapping("/users")
	public ResponseEntity<?> getUsers(
	        @RequestParam String sortType,
	        @RequestParam String sortOrder,
	        @RequestParam int limit,
	        @RequestParam int offset) {
	   
	        finalApiResponse result = secondApiService.getSortedUsers(sortType, sortOrder, limit, offset);
	        return ResponseEntity.ok(result);
	}
	    
	
	
	
	
}
