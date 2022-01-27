package com.freshbox.freshbox.model;

public class AuthenticationRequest {
	private String userid;
	private String password;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthenticationRequest(String userid, String password) {
		this.userid = userid;
		this.password = password;
	}
	public AuthenticationRequest() {
		
	}
	
	
	
	

}
