package com.board.app.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.app.DB_Preset;


public class User_Find {
	public static List<String> find(String email) {
		
    	List<String> list = new ArrayList<String>();
    	
    	final String sql = "SELECT user_id from web_user where email = ?";    	
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러 : " + e.getMessage());
		}
    	
    	return list;
	}
}
