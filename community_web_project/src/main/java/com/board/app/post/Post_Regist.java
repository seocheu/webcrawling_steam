package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.board.app.DB_Preset;


public class Post_Regist {
	public static void regist(String user_id, String title, String content) {
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    	
    	StringBuilder sb = new StringBuilder();
		final String sql = sb.append("INSERT INTO web_post VALUES((select (select count(*) from web_post)\r\n").append 
				("+ (select count(*) from web_post_del) from dual) + 1,\r\n").append  
				("?, ?, ?, 0, ?)").toString();
		
		final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
		try {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, user_id);
			pstmt.setTimestamp(4, currentTime);
			pstmt.executeUpdate();
			
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
	}
}
