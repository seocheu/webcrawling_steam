package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;

public class Reply_Edit {
	public static void edit(String post_id, String reply_id, String text) {
		final String sql = "UPDATE web_reply set text = ? where post_id = ? and reply_id = ?";
    	
		System.out.println(text+post_id+reply_id);
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
    		
			pstmt.setString(1, text);
			pstmt.setInt(2, Integer.parseInt(post_id));
			pstmt.setInt(3, Integer.parseInt(reply_id));
			
			pstmt.executeUpdate();
			
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
	}
}
