package com.board.app.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;

public class Reset_PW {
	public static boolean set(String user_id, String user_pw) {
    	boolean isFail = true;
		
    	final String sql = "UPDATE web_user set user_pw = ? where user_id = ?";
    	
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_id);
			
			final int row = pstmt.executeUpdate();
			
			if (row != 0) {
				isFail = false;
			}
			
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러 : " + e.getMessage());
		}

    	return !isFail;
	}
}
