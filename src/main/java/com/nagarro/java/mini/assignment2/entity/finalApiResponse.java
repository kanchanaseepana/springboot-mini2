package com.nagarro.java.mini.assignment2.entity;

import java.util.List;

public class finalApiResponse {
	
	private List<UserResponse> data;
	private PageInfo pageinformation;
	public List<UserResponse> getData() {
		return data;
	}
	public void setData(List<UserResponse> data) {
		this.data = data;
	}
	public PageInfo getPageinformation() {
		return pageinformation;
	}
	public void setPageinformation(PageInfo pageinformation) {
		this.pageinformation = pageinformation;
	}
	public finalApiResponse(List<UserResponse> data, PageInfo pageinformation) {
		super();
		this.data = data;
		this.pageinformation = pageinformation;
	}
	public finalApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "finalApiResponse [data=" + data + ", pageinformation=" + pageinformation + "]";
	}
	
	

}
