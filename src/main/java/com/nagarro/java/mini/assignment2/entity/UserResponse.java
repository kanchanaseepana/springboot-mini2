package com.nagarro.java.mini.assignment2.entity;

import java.time.LocalDateTime;

public class UserResponse {
	
	

    private String name;
    private int age;
    private String gender;
    private String dob;
    private String nationality;
    private String verificationStatus;
    

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String birthdate) {
		this.dob = birthdate;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	
	
	public UserResponse(String name, int age, String gender, String dob, String nationality,
			String verificationStatus) {
		super();
		//this.userId = userId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dob = dob;
		this.nationality = nationality;
		this.verificationStatus = verificationStatus;
		
	}
	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserResponse [name=" + name + ", age=" + age + ", gender=" + gender + ", dob="
				+ dob + ", nationality=" + nationality + ", verificationStatus=" + verificationStatus + "]";
	}
	
    
    

}
