package game_list;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select_Games {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		final String connectionURL = """
				jdbc:oracle:thin:@localhost:1521/xe
				""";
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(connectionURL, "WEBMASTER", "webmaster");
			StringBuilder stringBuilder = new StringBuilder();
			final String select_start = "SELECT * FROM games where "; 
			final String select_end = " order by name";
			boolean isRun = true;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			OutputStream outputStream = null;
			Writer writer = null;
			do {
				stringBuilder.setLength(0);
				System.out.println("SELECT * FROM games");
				System.out.print("WHERE ");
				String select_where = br.readLine();
				final String select_sql = stringBuilder.append(select_start).
						append(select_where).append(select_end).toString();
				System.out.println();
				try {
					preparedStatement = conn.prepareStatement(select_sql);
					resultSet = preparedStatement.executeQuery();
					String game_string = null;
					
					stringBuilder.setLength(0);
					String file_first = "C:\\Users\\seoch\\Desktop\\Code\\pj2_file\\result\\";
					String file_last = ".csv";
					System.out.print("파일 이름을 입력해주세요: ");
					String file_name = br.readLine();
					String file_string = stringBuilder.append(file_first).
							append(file_name).append(file_last).toString();
					outputStream = new FileOutputStream(file_string);
					writer = new OutputStreamWriter(outputStream, "cp949");
					
					while(resultSet.next()) {
						stringBuilder.setLength(0);
						for(int i = 1; i <= 8; i++) {
							if(i == 2)  {
								stringBuilder.append('"');
								stringBuilder.append(resultSet.getString(i));
								stringBuilder.append('"');
								stringBuilder.append(',');
							}
							else 
								stringBuilder.append(resultSet.getInt(i) + ",");
						}
						game_string = stringBuilder.toString();
		
						writer.write(game_string + "\r\n");
						writer.flush();
						
						System.out.println(game_string);
					}
					System.out.println("파일 출력이 완료되었습니다.\n");
					writer.close();
					outputStream.close();
					System.out.println("종료하시려면 end를 입력해주세요.");
					String end_string = br.readLine();
					if(end_string.equals("end") || end_string.equals("END"))
						isRun = false;
				} catch (SQLException e) {
					System.out.println("WHERE문을 정확히 입력해주세요.");
					System.out.println();
				}
			} while(isRun);
			br.close();
			resultSet.close();
			preparedStatement.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("종료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
