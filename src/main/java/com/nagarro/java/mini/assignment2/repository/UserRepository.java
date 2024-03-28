package com.nagarro.java.mini.assignment2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.java.mini.assignment2.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	@Query(value = "SELECT * FROM user ORDER BY user_id ASC", nativeQuery = true)
	List<User> findAllUsersOrderedById();

	default List<User> findUsersWithPagination(int limit, int offset) {
	    List<User> allUsers = findAllUsersOrderedById();
	    int startIndex = Math.min(offset, allUsers.size());
	    int endIndex = Math.min(offset + limit, allUsers.size());
	    return allUsers.subList(startIndex, endIndex);
	}

}
