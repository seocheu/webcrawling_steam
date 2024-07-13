package com.board.app.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.app.DB_Preset;

public class Double_Check {
	public static boolean check(String user_id) {

		boolean isDouble = false;
			
		final String sql = "SELECT count(user_id) from web_user where user_id = ?";
		final PreparedStatement pstmt = DB_Preset.getPstmt(sql);
		try {
			pstmt.setString(1, user_id);
			final ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) != 0)
					isDouble = true;
			}
			rs.close();
			DB_Preset.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러: " + e.getMessage());
		}	

		return isDouble;
	}
}
