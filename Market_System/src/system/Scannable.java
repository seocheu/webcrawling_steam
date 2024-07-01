package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface Scannable {
	public static final BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));
	static String scan() {
		String str = null;
		try {
			str = br.readLine();
		} catch (IOException e) {
			System.out.println("에러: " + e.getMessage());
		} 
		
		return str;
	}
	
	static int scanNum() {
		boolean isRun = true;
		int num = 0;
		do {
			String strNum = scan();
			try {
				num = Integer.parseInt(strNum);
				isRun = false;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.print("숫자를 입력해주세요: ");
			}
		} while(isRun);
		
		return num;
	}
}
