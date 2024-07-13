package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.app.DB_Preset;

public class Post_Page {
	public static int size() {
		int page_size = 0;
		final String sql = "SELECT count(*) from web_post";
		final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
		try {
			final ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				page_size = rs.getInt(1);
			
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return page_size;
	}
}
