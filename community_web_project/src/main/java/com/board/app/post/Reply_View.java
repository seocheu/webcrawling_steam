package com.board.app.post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.board.app.DB_Preset;
import com.board.app.Reply_Info;


public class Reply_View {
	public static List<Reply_Info> view(int post_id) {
		List<Reply_Info> list = new ArrayList<Reply_Info>();
		
		final String sql = "SELECT reply_id, user_id, (SELECT name from web_user wu where wu.user_id = wr.user_id), text from web_reply wr where post_id = ? order by reply_id";
    	
    	final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
    	try {
    		pstmt.setInt(1, post_id);
			final ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Reply_Info(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
    	
    	return list;
	}
	
	public static Reply_Info get(int reply_id, int post_id) {
		Reply_Info reply = null;
		final String sql = "SELECT reply_id, user_id, text from web_reply where reply_id = ? and post_id = ?";
		
		PreparedStatement pstmt = DB_Preset.getPstmt(sql);
		try {
			pstmt.setInt(1, reply_id);
			pstmt.setInt(2, post_id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reply = new Reply_Info(rs.getInt(1), rs.getString(2), "", rs.getString(3));
			}
			
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}
		return reply;
	}
}
