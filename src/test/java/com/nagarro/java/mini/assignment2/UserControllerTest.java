package com.nagarro.java.mini.assignment2;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.java.mini.assignment2.controller.UserController;
import com.nagarro.java.mini.assignment2.service.RandomUserApi;
import com.nagarro.java.mini.assignment2.entity.*;
import com.nagarro.java.mini.assignment2.service.*;
import com.nagarro.java.mini.assignment2.exception.*;
import com.nagarro.java.mini.assignment2.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private RandomUserApi userService;
    
    @Mock
    private secondApiService secondApiService;
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;
    
    
    
   

    @Autowired
    private ObjectMapper objectMapper;
    

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    void testGetUsersWithPagination_Success() throws Exception {
        // Mocking the service response
        List<UserResponse> userList = Arrays.asList(
                new UserResponse(
                        "Sharlene West",
                        34,
                        "FEMALE",
                        "10 Mar 1989",
                        "US",
                        "TO_BE_VERIFIED"
                )
        );

        PageInfo pageInfo = new PageInfo(true, true, 14);
        finalApiResponse expectedApiResponse = new finalApiResponse(userList, pageInfo);

        // Mock the service call
        when(secondApiService.getSortedUsers(any(String.class), any(String.class), any(Integer.class), any(Integer.class)))
                .thenReturn(expectedApiResponse);

        // Performing the request with limit, offset, and sort parameters
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .param("sortType", "Name")
                .param("sortOrder", "EVEN")
                .param("limit", "1")
                .param("offset", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(expectedApiResponse)));
    }
    
    
    @Test
    void testCreateUsers_Success() throws Exception {
        // Mocking the service response
        List<UserResponse> expectedUserResponses = Arrays.asList(
                new UserResponse("John Doe", 25, "MALE", "01 Jan 1998", "US", "TO_BE_VERIFIED"),
                new UserResponse("Jane Smith", 30, "FEMALE", "15 Feb 1993", "CA", "VERIFIED")
        );

        // Mock the service call
        when(userService.createUsers(any(Integer.class)))
                .thenReturn(expectedUserResponses);

        // Performing the POST request to create users
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserCreationRequest(2))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedUserResponses)));
    }
    
    @Test
    void testCreateUsers_WithSize_SuccessOne() {
        // Mocking
        UserCreationRequest request = new UserCreationRequest(5);
        List<UserResponse> mockResponse = Arrays.asList(new UserResponse(), new UserResponse());

        when(userService.createUsers(request.getSize())).thenReturn(mockResponse);

        // Execution
        ResponseEntity<?> responseEntity = userController.createUsers(request);

       
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof List);
        assertEquals(2, ((List<?>) responseEntity.getBody()).size());
    }
    

    
    @Test
    void testCreateUsers_InvalidSize() throws Exception {
        int invalidSize = 10;

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserCreationRequest(invalidSize))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid size, size must be within 5"));
    }
    
    @Test
    void testCreateUsers_InvalidNegativeSize() throws Exception {
        int invalidSize = -3;

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserCreationRequest(invalidSize))))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid size, size must be within 5"));
    }
    
    @Test
    void testGetUsersWithPagination_Success_Age_even() throws Exception {
        // Mocking the service response
        List<UserResponse> userList = Arrays.asList(
                new UserResponse(
                        "Sharlene West",
                        34,
                        "FEMALE",
                        "10 Mar 1989",
                        "US",
                        "TO_BE_VERIFIED"
                ),
                new UserResponse(
                        "Hannah White",
                        50,
                        "FEMALE",
                        "03 Jun 1973",
                        "CA",
                        "TO_BE_VERIFIED"
                )
        );

        PageInfo pageInfo = new PageInfo(true, true, 17);
        finalApiResponse expectedApiResponse = new finalApiResponse(userList, pageInfo);

        // Mock the service call
        when(secondApiService.getSortedUsers(any(String.class), any(String.class), any(Integer.class), any(Integer.class)))
                .thenReturn(expectedApiResponse);

        // Performing the request with limit, offset, and sort parameters
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .param("sortType", "Age")
                .param("sortOrder", "EVEN")
                .param("limit", "2")
                .param("offset", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(expectedApiResponse)));
    }
    
    @Test
    void testGetUsersWithPagination_Success_Age_Odd() throws Exception {
        // Mocking the service response
        List<UserResponse> userList = Arrays.asList(
                new UserResponse(
                        "Dana Aubert",
                        31,
                        "FEMALE",
                        "12 Dec 1991",
                        "CH",
                        "TO_BE_VERIFIED"
                ),
                new UserResponse(
                        "Olivia Slawa",
                        48,
                        "FEMALE",
                        "10 Dec 1974",
                        "CA",
                        "TO_BE_VERIFIED"
                )
        );

        PageInfo pageInfo = new PageInfo(true, true, 17);
        finalApiResponse expectedApiResponse = new finalApiResponse(userList, pageInfo);

        // Mock the service call
        when(secondApiService.getSortedUsers(any(String.class), any(String.class), any(Integer.class), any(Integer.class)))
                .thenReturn(expectedApiResponse);

        // Performing the request with limit, offset, and sort parameters
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .param("sortType", "Age")
                .param("sortOrder", "ODD")
                .param("limit", "2")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(expectedApiResponse)));
    }
    

    @Test
    void testGetUsers_Success_Name_Even() {
        // Mocking
        String sortType = "Name";
        String sortOrder = "EVEN";
        int limit = 5;
        int offset = 0;

        finalApiResponse mockResponse = new finalApiResponse();

        when(secondApiService.getSortedUsers(sortType, sortOrder, limit, offset)).thenReturn(mockResponse);

        // Execution
        ResponseEntity<?> responseEntity = userController.getUsers(sortType, sortOrder, limit, offset);

        // Assertion
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody() instanceof finalApiResponse);
    }


@Test
void testGetUsers_Success_Name_Odd() {
    // Mocking
    String sortType = "Name";
    String sortOrder = "Odd";
    int limit = 5;
    int offset = 0;

    finalApiResponse mockResponse = new finalApiResponse();

    when(secondApiService.getSortedUsers(sortType, sortOrder, limit, offset)).thenReturn(mockResponse);

    // Execution
    ResponseEntity<?> responseEntity = userController.getUsers(sortType, sortOrder, limit, offset);

    // Assertion
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertTrue(responseEntity.getBody() instanceof finalApiResponse);
}
}


    
    
    
    
    
 



    

 

    
    
    



