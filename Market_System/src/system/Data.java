package system;

import java.util.Calendar;

public class Data {
	
	private static int index;
	private static int value;
	private static long seconds;
	private final static int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static int[] time(long second) {
		int[] time = new int[3];	// 시 분 초
		
		long temp1 = second / 1000;
		long temp2 = temp1 / 3600;
		time[0] = (int)temp2;
		temp2 =  temp1 % 3600;
		time[1] = (int)temp2 / 60;
		time[2] = (int)temp2 % 60;
		
		return time;
	}
	
	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		Data.index = index;
	}

	public static int getValue() {
		return value;
	}

	public static void setValue(int value) {
		Data.value = value;
	}

	public static long getSeconds() {
		return seconds;
	}

	public static void setSeconds(long seconds) {
		Data.seconds = seconds;
	}

	public static int[] getMonth() {
		return month;
	}

	public static int seconds(int[] time) {
		int second = 0;
		Calendar calendar = Calendar.getInstance();
		second = ((time[0] - calendar.get(Calendar.YEAR)) * 365 + day(time[1], calendar.get(Calendar.MONTH) + 1)
				+ time[2] - calendar.get(Calendar.DAY_OF_MONTH)) * 86400	// 86400 = 24 * 60 * 60
				+ (time[3] - calendar.get(Calendar.HOUR_OF_DAY)) * 3600 - (time[4] - calendar.get(Calendar.MINUTE)) * 60
				- calendar.get(Calendar.SECOND);
		return second;
	}
	
	private static int day(int expire, int now) {
		int day = 0;
		for (int i = 0; i < expire; i++) {
			day += month[i];
		}
		for (int i = 0; i < now; i++) {
			day -= month[i];
		}
		return day;
	}
}
