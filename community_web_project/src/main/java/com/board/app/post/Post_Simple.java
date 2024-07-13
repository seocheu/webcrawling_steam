package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.app.DB_Preset;
import com.board.app.Post_Info;


public class Post_Simple {

	public static List<Post_Info> getList(String page_str) {
		List<Post_Info> post_list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		final String sql = sb.append("SELECT wp.post_id, wp.title, wp.content, wp.user_id, wp.post_date, wp.view_cnt,\r\n").append
				("(SELECT name from web_user wu where wp.user_id = wu.user_id ) as user_name,\r\n").append
				("(SELECT count(*) from web_like wl where wl.post_id = wp.post_id ) as like_cnt,\r\n").append
				("(SELECT count(*) from web_reply wr where wr.post_id = wp.post_id ) as reply_cnt, wp.rnum\r\n").append 
				("FROM ( SELECT p.*, ROWNUM rnum FROM (SELECT * FROM web_post order by post_id DESC) p\r\n").append
				("where ROWNUM <= ?) wp where wp.rnum >= ? order by wp.post_id DESC").toString();

		final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
		
		int page;
		try {
			page = Integer.parseInt(page_str) * 10;
		} catch (NumberFormatException e) {
			page = 10;
		}
		
		try {	
			pstmt.setInt(1, page);
			pstmt.setInt(2, page - 9);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
					post_list.add(new Post_Info(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(7), rs.getString(5), rs.getInt(6), rs.getInt(8), rs.getInt(9)));
			}
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러 : " + e.getMessage());
		}
		
		return post_list;
	}
}
