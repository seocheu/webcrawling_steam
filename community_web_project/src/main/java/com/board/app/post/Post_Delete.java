package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;


public class Post_Delete {
	public static void delete(String post_id) {
    	
    	String sql = "INSERT into web_post_del values (?)";
    	
    	PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.executeUpdate();
			
			DB_Preset.closeConn();
			
			sql = "DELETE from web_post where post_id = ?";
			pstmt = DB_Preset.getPstmt(sql);
			pstmt.setInt(1, Integer.parseInt(post_id));
			
			pstmt.executeUpdate();
			
			DB_Preset.closeConn();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
	}
}
