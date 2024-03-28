package com.nagarro.java.mini.assignment2.service.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nagarro.java.mini.assignment2.entity.User;
import com.nagarro.java.mini.assignment2.entity.UserResponse;
import com.nagarro.java.mini.assignment2.service.*;

@Component
public class NameSortingStrategy implements UserSortingStrategy {
    @Override
    public void sort(List<UserResponse> users,String type) {
        Comparator<UserResponse> nameLengthComparator = Comparator.comparingInt(user -> user.getName().length());

        List<UserResponse> oddLengthUsers = users.stream()
                .filter(user -> user.getName().length() % 2 != 0)
                .sorted(nameLengthComparator)
                .collect(Collectors.toList());

        List<UserResponse> evenLengthUsers = users.stream()
                .filter(user -> user.getName().length() % 2 == 0)
                .sorted(nameLengthComparator)
                .collect(Collectors.toList());

        users.clear();
        if(type.equalsIgnoreCase("ODD")) {
        users.addAll(oddLengthUsers);
        users.addAll(evenLengthUsers);
        }else {
        	users.addAll(evenLengthUsers);
        	users.addAll(oddLengthUsers);
        	
        }
    }
}
