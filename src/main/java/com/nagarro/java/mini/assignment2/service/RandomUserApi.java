package com.nagarro.java.mini.assignment2.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.naming.Name;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nagarro.java.mini.assignment2.entity.*;
import com.nagarro.java.mini.assignment2.exception.*;
import com.nagarro.java.mini.assignment2.repository.UserRepository;


@Service
public class RandomUserApi {
	
	@Autowired
	private ExecutorService executorService;
	
	@Autowired
    @Qualifier("webClientApi1")
    private WebClient webClientApi1;

    @Autowired
    @Qualifier("webClientApi2")
    private WebClient webClientApi2;

    @Autowired
    @Qualifier("webClientApi3")
    private WebClient webClientApi3;
    
    
    @Autowired
    private UserRepository userRepository;
    


    public String getRandomUser() {
        String getrandomuser =  webClientApi1.get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return getrandomuser;
        
    }

    public String getNationality(String name) {
        String nation= webClientApi2.get()
                .uri("?name=" + name)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return nation;
    }

    public String getGender(String name) {
        String gender= webClientApi3.get()
                .uri("?name=" + name)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return gender;
    }
    
    
    private HashMap<String, Object> jsonParse(String user){
    	ObjectMapper objectMapper = new ObjectMapper();
    	try {
    		return objectMapper.readValue(user, HashMap.class);
    	}catch(IOException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    private HashMap<String, Object> getUser(){
    	return jsonParse(getRandomUser());
    }
    
    

    
    //3
    public List<UserResponse> createUsers(int size){
    	
    	
    	List<User> users = new ArrayList<>();
    	List<UserResponse> response = new ArrayList<>();   	
    	try {
    		
    		for(int i=0;i<size;i++) {
    			User newUser = new User();
    			UserResponse responseUser= new UserResponse();
    			HashMap<String, Object> user = getUser();
    			if(user!=null) {
    				List<Map<String, Object>> resultList= (List<Map<String, Object>>) user.get("results");
    				if(resultList !=null && !resultList.isEmpty()) {
    					Map<String, Object> getfirstUser = resultList.get(0);
    					String gender = (String) getfirstUser.get("gender");
    					Map<String, Object> name=(Map<String, Object>) getfirstUser.get("name");
    					String firstname=(String) name.get("first");
    					System.out.println(firstname);
    					String lastname=(String) name.get("last");
    					System.out.println(lastname);
    					String nation=(String) getfirstUser.get("nat");
    					Map<String, Object> dob = (Map<String, Object>) getfirstUser.get("dob");
    					int age =(int) dob.get("age");
    					String dobDate=(String) dob.get("date");
    					String Date=dobDate.substring(0,10);
    					String birthdate=dateFormat(Date);
    					
    					
    					HashMap<String, Object> genderAndNation=executeParallelApiCallsGenderAndNation(firstname);
    					HashMap<String, Object> Result=(HashMap<String, Object>) responseResult(genderAndNation);
    					
    					String genderapiResponse=(String) Result.get("gender");
    					List<String> countriesapiResponse =(List<String>) Result.get("countries");
    					
    					String verificationStatus=validateUser(gender,genderapiResponse,countriesapiResponse,nation);
    					
    					
    					
    					//newUser.setUserId(newUser.getUserId());
    					newUser.setAge(age);
    					newUser.setDateCreated(LocalDateTime.now());
    					newUser.setDateModified(LocalDateTime.now());
    					newUser.setDob(birthdate);
    					if(gender.equalsIgnoreCase("female")) {
    						newUser.setGender("FEMALE");
    					}else {
    						newUser.setGender("MALE");
    					}
    					newUser.setName(firstname+ " "+lastname);
    					newUser.setNationality(nation);
    					newUser.setVerificationStatus(verificationStatus);
    					
    					
    					createUsers(newUser);
    					users.add(newUser);
    					responseUser.setName(firstname+ " "+lastname);
    					responseUser.setDob(birthdate);
    					responseUser.setGender(gender);
    					responseUser.setAge(age);
    					responseUser.setNationality(nation);
    					responseUser.setVerificationStatus(verificationStatus);
    					response.add(responseUser);
    					
    					
    				
    					
    					
    				}
    				
    			}
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return response;
    	
    }
    

    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    

    
    
    
    
    
    
    public static Map<String, Object> responseResult(Map<String, Object> apiResponse){
    	
    	try {
    		//extract gender
    		Map<String, Object> genderMap = (Map<String, Object>) apiResponse.get("gender");
    		String gender = (String) genderMap.get("gender");
    		
    		//Extract country_ids into list
    		List<String> countries=new ArrayList<>();
    		HashMap<String, Object> nationMap=(HashMap<String, Object>) apiResponse.get("nationalities");
    		List<HashMap<String, Object>> countryList=(List<HashMap<String, Object>>) nationMap.get("country");
    		for(HashMap<String, Object> country : countryList) {
    			String countryId=(String) country.get("country_id");
    			countries.add(countryId);
    			
    		}
    		System.out.println(countries);
    		//create a map to hold extract response
    		HashMap<String, Object> extractResponse=new HashMap();
    		extractResponse.put("gender", gender);
    		extractResponse.put("countries", countries);
    		
    		return extractResponse;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return new HashMap<>();
    	}
    	
    }
    
    
    
    
    public static String dateFormat(String randomdate) {
    	DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMM yyyy");
    	
    	LocalDate localdate = LocalDate.parse(randomdate,input);
    	
    	String format=localdate.format(output);
    	return format;
    	
    }
    
    


    
    public HashMap<String, Object> executeParallelApiCallsGenderAndNation(String name) {
	HashMap<String, Object> map = new HashMap<String, Object>();
	CompletableFuture<String> nationalityFuture = CompletableFuture.supplyAsync(() -> getNationality(name),executorService);
	CompletableFuture<String> genderFuture = CompletableFuture.supplyAsync(() -> getGender(name), executorService);
	CompletableFuture<Void> allOf = CompletableFuture.allOf(nationalityFuture, genderFuture);
	try
	{
		try {
		allOf.get();
		} catch (ExecutionException e) {
			e.printStackTrace();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
			}
		
		
		
	// Get the results from both CompletableFutures HashMap<String, Object>
    HashMap<String, Object> resultMap1=jsonParse(nationalityFuture.join());
    HashMap<String, Object> resultMap2=jsonParse(genderFuture.join());
    
		 map.put("nationalities",resultMap1);
		 map.put("gender",resultMap2);
		 return map;

    }
    
 
    public String validateUser(
            String gender,
            String genderapiResponse,
            List<String> countriesapiResponse,
            String nation) {

    	boolean isGenderValid =  gender.equalsIgnoreCase(genderapiResponse);
        boolean isNationalityValid =countriesapiResponse.contains(nation);

        

        // Mark the user accordingly
        if (isNationalityValid && isGenderValid) {
            return "VERIFIED";
        } else {
            return "TO_BE_VERIFIED";
        }
    }
    
  
    @Transactional
    public void createUsers(User user) {
        

        userRepository.save(user);
    }
   
   
	
	

}

