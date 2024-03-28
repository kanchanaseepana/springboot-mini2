package com.nagarro.java.mini.assignment2.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long userId;

	    private String name;
	    private int age;
	    private String gender;
	    private String dob;
	    private String nationality;
	    private String verificationStatus;
	    private LocalDateTime dateCreated;
	    private LocalDateTime dateModified;
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
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
		public LocalDateTime getDateCreated() {
			return dateCreated;
		}
		public void setDateCreated(LocalDateTime dateCreated) {
			this.dateCreated = dateCreated;
		}
		public LocalDateTime getDateModified() {
			return dateModified;
		}
		public void setDateModified(LocalDateTime dateModified) {
			this.dateModified = dateModified;
		}
		public User(Long userId, String name, int age, String gender, String dob, String nationality,
				String verificationStatus, LocalDateTime dateCreated, LocalDateTime dateModified) {
			super();
			this.userId = userId;
			this.name = name;
			this.age = age;
			this.gender = gender;
			this.dob = dob;
			this.nationality = nationality;
			this.verificationStatus = verificationStatus;
			this.dateCreated = dateCreated;
			this.dateModified = dateModified;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", name=" + name + ", age=" + age + ", gender=" + gender + ", dob=" + dob
					+ ", nationality=" + nationality + ", verificationStatus=" + verificationStatus + ", dateCreated="
					+ dateCreated + ", dateModified=" + dateModified + "]";
		}
    

}
