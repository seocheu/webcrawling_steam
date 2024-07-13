package com.board.app.user;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.board.app.DB_Preset;

public class User_Insert {
	public static boolean insert(String user_id, String user_pw, String name, String email) {
		boolean isFail = false;

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		final String sql = "INSERT INTO web_user VALUES(?, ?, ?, ?, ?)";

		try {
			final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setTimestamp(5, currentTime);
			pstmt.executeUpdate();

			DB_Preset.closeConn();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			isFail = true;
		}
		
		return !isFail;
	}
}
