package com.board.app;

public class User_Info {
	private String user_id;
	private String user_name;
	private String email;
	private String date;
	
	public String getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getEmail() {
		return email;
	}
	public String getDate() {
		return date;
	}
	
	public User_Info(String user_id, String user_name, String email, String date) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.email = email;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "User_Info [user_id=" + user_id + ", user_name=" + user_name + ", email=" + email + ", date=" + date
				+ "]";
	}
	
	
}
