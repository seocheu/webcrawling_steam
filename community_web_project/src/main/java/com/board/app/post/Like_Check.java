package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.app.DB_Preset;


public class Like_Check {
	public static boolean check(String user_id, int post_id) { 
		int value = 0;
		final String sql = "select count(*) from web_like where user_id = ? and post_id = ?";
		
		final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
		try {
			pstmt.setString(1, user_id);
			pstmt.setInt(2, post_id);
			final ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				value = rs.getInt(1);
			}
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		
		if(value == 0)
			return false;
		else 
			return true;
	}
}
