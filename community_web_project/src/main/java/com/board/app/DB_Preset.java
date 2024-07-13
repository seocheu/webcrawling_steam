package com.board.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB_Preset {
	private static Connection conn;
	private static PreparedStatement pstmt;
	
	public static PreparedStatement getPstmt(String sql) {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			final String connURL = "jdbc:oracle:thin:@localhost:1521/xe";
			conn = DriverManager.getConnection(connURL, "WEBMASTER", "webmaster");
			pstmt = conn.prepareStatement(sql);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러:"+e.getMessage());
		}
		
		return pstmt;
		
	}
	
	public static void closeConn() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("에러:"+e.getMessage());
		}
	}
}
