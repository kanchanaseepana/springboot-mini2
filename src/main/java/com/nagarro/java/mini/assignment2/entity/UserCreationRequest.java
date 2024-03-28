package com.nagarro.java.mini.assignment2.entity;

public class UserCreationRequest {
	
	 private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public UserCreationRequest(int size) {
		super();
		this.size = size;
	}

	public UserCreationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserCreationRequest [size=" + size + "]";
	}
	 
	 

}
