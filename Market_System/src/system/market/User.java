package system.market;

import system.Data;
import system.function.Sign;

public class User extends Thread implements Sign {
	private static User user = new User();
	private User() { }
	public static User getInstance() {
		return user;
	}
	
	private static User_Data currentUser;
	
	private static User_Data[] users;
	public static User_Data[] users() {
		return users;
	}
	public static void reDataUsers(User_Data[] ud) {
		users = ud;
	}
	
	public void sign(String id, String pw) {
		signUp(users, id, pw);
	}
	
	public void setUser() {
		currentUser = users[Data.getIndex()];
		currentUser.setStartWorkTime(System.currentTimeMillis());
	}
	
	public static User_Data getUser() {
		return currentUser;
	}
}
