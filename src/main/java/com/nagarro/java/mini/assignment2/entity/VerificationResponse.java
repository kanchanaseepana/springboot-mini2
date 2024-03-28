package com.nagarro.java.mini.assignment2.entity;
import java.time.LocalDate;

public class VerificationResponse {
	
	private String firstname;
	private String lastname;
    private String dob;
    private String gender;
    private int age;
    private String nationality;
    private String verificationStatus;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	public VerificationResponse(String firstname, String lastname, String dob, String gender, int age,
			String nationality, String verificationStatus) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.gender = gender;
		this.age = age;
		this.nationality = nationality;
		this.verificationStatus = verificationStatus;
	}
	public VerificationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "VerificationResponse [firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob + ", gender="
				+ gender + ", age=" + age + ", nationality=" + nationality + ", verificationStatus="
				+ verificationStatus + "]";
	}
    
    
	
	

}
