package system;

import java.util.Calendar;
import java.util.regex.Pattern;

import system.market.Market;
import system.market.Product_Data;
import system.market.User_Data;

public interface Checkable {
	
	static boolean checkName(Object[] objs, String name) {
		int size = checkSize(objs);
		/**	---------- 유저 ---------------------- */
		if(objs instanceof User_Data[]) {
			User_Data[] users = (User_Data[])objs;
			
			for(int i = 0; i < size; i++) {
				if(users[i].getId().equals(name)) {
					Data.setIndex(i);
					return true;
				}
			}
		/**	---------- 상품 ---------------------- */
		} else {
			Product_Data[] products = (Product_Data[])objs;
			
			for(int i = 0; i < size; i++) {
				if(products[i].getName().equals(name)) {
					Data.setIndex(i);
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean checkName(Product_Data[] pds, String name, boolean isAdult) {
		
		int size = checkSize(pds);
		
		Product_Data[] products = (Product_Data[])pds;
		
		for(int i = 0; i < size; i++) {
			if(products[i].getName().equals(name) && products[i].isAdult() == isAdult) {
				if(Market.menuType == Menu_Type.SELL && checkStale(pds[i]))
					return false;
				Data.setIndex(i);
				return true;
			}
		}
		return false;
	}
	
	static boolean adultVerify() {
		boolean isRun = true;
		int verify;
		System.out.print("생년월일을 입력해주세요: ");
		do {
			verify = Scannable.scanNum();
			if(verify > 99999 && verify < 1000000)
				isRun = false;
			else
				System.out.print("정확한 생년월일을 입력해주세요: ");
		} while(isRun);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR) % 100;
		int checkYear = verify / 10000;
			
		if(checkYear > year || checkYear < (year - 20)) {
				System.out.println("확인되었습니다.");
				return true;
		} else {
			int monthDay = (now.get(Calendar.MONTH) + 1) * 100 + now.get(Calendar.DAY_OF_MONTH);
			int checkMonthDay = verify % 10000;
			if(checkYear == year - 19 && checkMonthDay <= monthDay) {
				System.out.println("확인되었습니다.");
				return true;
			}
		}
			
		System.out.println("청소년 구매 불가 상품입니다.");
		return false;
	}
	
	static boolean stringVerify(String str, String pattern) {
		if(Pattern.matches(pattern, str))
			return true;
		else
			return false;
	}
	
	static boolean checkStale(Product_Data pd) {
		long time = Long.sum(pd.getExpireTime(), -System.currentTimeMillis());
		Data.setSeconds(time);
		if(pd.isExpire() && time < 60000) {
			return true;
		}
		return false;
	}
	
	static int[] parseDate(String date) {
		int[] time = new int[5];
		String[] strTime = new String[5];
		strTime = date.split("-| |:");
		try {
			for(int i = 0; i < time.length; i++) {
				time[i] = Integer.parseInt(strTime[i]);
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return time;
	}
	
	static int checkSize(Object[] objs) {
		int size;
		if(objs == null)
			size = 0;
		else
			size = objs.length;
		
		return size;
	}
}
