package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;


public class Like_Undo {
	public static void unlike(String user_id, int post_id) {
		final String sql = "DELETE from web_like where user_id = ? and post_id = ?";
    	
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setString(1, user_id);
			pstmt.setInt(2, post_id);

			pstmt.executeUpdate();
			
			DB_Preset.closeConn();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
	}
}
