package com.nagarro.java.mini.assignment2.entity;

public class PageInfo {
	
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	private int total;
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public PageInfo(boolean hasPreviousPage, boolean hasNextPage, int total) {
		super();
		this.hasPreviousPage = hasPreviousPage;
		this.hasNextPage = hasNextPage;
		this.total = total;
	}
	public PageInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PageInfo [hasPreviousPage=" + hasPreviousPage + ", hasNextPage=" + hasNextPage + ", total=" + total
				+ "]";
	}
	
	
	
	
	

}
