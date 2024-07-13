package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;


public class Post_Edit {
	public static void edit(String post_id, String title, String content) {
    	
    	final String sql = "UPDATE web_post set title = ?, content = ? where post_id = ?";
    	
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, Integer.parseInt(post_id));
			
			pstmt.executeUpdate();
			
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
	}
}
