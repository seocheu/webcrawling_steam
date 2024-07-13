package com.board.app.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;
import com.board.app.User_Info;

public class User_Edit {
	public static User_Info edit(String name, String email, User_Info user) {
    	
    	final String sql = "UPDATE web_user set name = ?, email = ? where user_id = ?";
    	
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, user.getUser_id());
			
			pstmt.executeUpdate();
			
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
    	
    	return new User_Info(user.getUser_id(), name, email, user.getDate());
	}
}
