package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.app.DB_Preset;
import com.board.app.Post_Info;


public class Post_View {
	public static Post_Info view(int post_id, int prev_post) {
		Post_Info post_info = null;
		String sql = null;
		PreparedStatement pstmt = null;
		try {
			if(post_id != prev_post) {
				sql = "update web_post SET view_cnt = (select view_cnt "
						+ "from web_post where post_id = ?) + 1 where post_id = ?";
				pstmt = DB_Preset.getPstmt(sql);
				pstmt.setInt(1, post_id);
				pstmt.setInt(2, post_id);
				pstmt.executeUpdate();
				
				DB_Preset.closeConn();
			}
			
			sql = "SELECT wp.*, (SELECT name from web_user wu where wp.user_id = wu.user_id) user_name,"
					+ "(SELECT count(post_id) from web_like where post_id = ?) wl,\r\n" +
					"(SELECT count(post_id) from web_reply where post_id = ?) wr from web_post wp where wp.post_id = ?";
			pstmt = DB_Preset.getPstmt(sql);
			
			pstmt.setInt(1, post_id);
			pstmt.setInt(2, post_id);
			pstmt.setInt(3, post_id);
			final ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				post_info = new Post_Info(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(7), rs.getString(6), rs.getInt(5), rs.getInt(8), rs.getInt(9));
			}
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("에러: " + e.getMessage());
		}
		
		return post_info;
	}
}
