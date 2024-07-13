package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.board.app.DB_Preset;

public class Reply_Delete {
	public static void delete(String post_id, String reply_id) {
		final String sql = "DELETE from web_reply where post_id = ? and reply_id = ?";
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
			pstmt.setInt(1, Integer.parseInt(post_id));
			pstmt.setInt(2, Integer.parseInt(reply_id));
			
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
