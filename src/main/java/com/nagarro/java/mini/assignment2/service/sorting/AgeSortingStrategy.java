package com.nagarro.java.mini.assignment2.service.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.nagarro.java.mini.assignment2.entity.User;
import com.nagarro.java.mini.assignment2.entity.UserResponse;

@Component
@Primary
public class AgeSortingStrategy implements UserSortingStrategy {
    @Override
    public void sort(List<UserResponse> users,String type) {
        Comparator<UserResponse> ageComparator = Comparator.comparingInt(UserResponse::getAge);

        List<UserResponse> evenUsers = users.stream()
                .filter(user -> user.getAge() % 2 == 0)
                .sorted(ageComparator)
                .collect(Collectors.toList());

        List<UserResponse> oddUsers = users.stream()
                .filter(user -> user.getAge() % 2 != 0)
                .sorted(ageComparator)
                .collect(Collectors.toList());

        users.clear();
        if(type.equalsIgnoreCase("EVEN")) {
        users.addAll(evenUsers);
        users.addAll(oddUsers);
        }else {
        	users.addAll(oddUsers);
        	users.addAll(evenUsers);
        	
        }
    }
}