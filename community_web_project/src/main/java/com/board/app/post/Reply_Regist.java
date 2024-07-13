package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;


public class Reply_Regist {
	public static void regist(String post_id, String user_id, String text) {

    	final String sql = "INSERT into web_reply values ((SELECT count(post_id) from web_reply where post_id = ?) + 1, ?, ?, ?)";
    	    	
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.setInt(2, Integer.parseInt(post_id));
	    	pstmt.setString(3, user_id);
	    	pstmt.setString(4, text);
	    	
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
