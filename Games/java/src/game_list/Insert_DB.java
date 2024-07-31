package game_list;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert_DB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		final String connectionURL = """
				jdbc:oracle:thin:@localhost:1521/xe
				""";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(connectionURL, "WEBMASTER", "webmaster");
			final String insert_sql = """
					INSERT INTO games(id, name, rating,\s
					rel_year, rel_month, ori_price, sale, fin_price)\s
					VALUES(?, ?, ?, ?, ?, ?, ?, ?)
					""";
			final InputStream inputStream = new FileInputStream("C:\\Users\\seoch\\Desktop\\Code\\pj2_file\\Game_List.csv");
			final Reader reader = new InputStreamReader(inputStream);
			final BufferedReader br = new BufferedReader(reader);
			
			final PreparedStatement preparedStatement = conn.prepareStatement(insert_sql);
			br.readLine();	// 컬럼명 스킵
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				
				String[] gameInfo = str.split(",");
				int temp = 0;
				for(int i = 0; i < 8; i++) {	// 컬럼의 수 8개
					if(i == 1) {	// 2번 째 데이터는 String 타입 (게임 이름)
						if(gameInfo[1].charAt(0) == '"') {
							String name = gameInfo[1].substring(1, gameInfo[1].length()) + "," +
									gameInfo[2].substring(0, gameInfo[2].length() - 1);
							preparedStatement.setString(2, name);
							temp++;
						}
						else
							preparedStatement.setString(2, gameInfo[1]);
					} else if (i == 2 && gameInfo[2 + temp].equals("-1")){		// 평점이 존재하지 않을 경우
						preparedStatement.setNull(3, java.sql.Types.INTEGER);
					}else {
						preparedStatement.setInt(i + 1, Integer.parseInt(gameInfo[i + temp]));
					}
				}
				final int row = preparedStatement.executeUpdate();
				System.out.println(gameInfo[1] + " " + row + "행 추가");
			}
			preparedStatement.close();
			br.close();
			reader.close();
			inputStream.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("에러: " + e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
