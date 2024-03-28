 package com.nagarro.java.mini.assignment2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.nagarro.java.mini.assignment2.entity.User;
import com.nagarro.java.mini.assignment2.entity.UserResponse;
import com.nagarro.java.mini.assignment2.service.sorting.UserSortingStrategy;

@Service
public class UserSortContext {
	
	@Autowired
	private UserSortingStrategy sortingStrategy;

//    public UserSortContext(UserSortingStrategy sortingStrategy) {
//        this.sortingStrategy = sortingStrategy;
//    }

    public void setSortingStrategy(UserSortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void performSort(List<UserResponse> userList,String type) {
        sortingStrategy.sort(userList,type);
    }

}
