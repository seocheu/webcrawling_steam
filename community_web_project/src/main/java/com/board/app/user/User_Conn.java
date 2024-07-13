package com.board.app.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.app.DB_Preset;
import com.board.app.User_Info;

public class User_Conn {
	public static User_Info conn(String user_id, String user_pw) {
		User_Info user = null;
		final String sql = "SELECT * from web_user where user_id = ? and user_pw = ?";
		final PreparedStatement pstmt = DB_Preset.getPstmt(sql);

		try {
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			final ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User_Info(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("오류:" + e.getMessage());
		}

		return user;
	}
}
