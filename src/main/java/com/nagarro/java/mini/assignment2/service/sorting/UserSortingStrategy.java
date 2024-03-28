package com.nagarro.java.mini.assignment2.service.sorting;

import java.util.List;

import com.nagarro.java.mini.assignment2.entity.User;
import com.nagarro.java.mini.assignment2.entity.UserResponse;

public interface UserSortingStrategy {
	
	void sort(List<UserResponse> users,String type);

}
