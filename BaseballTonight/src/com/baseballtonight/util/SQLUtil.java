package com.baseballtonight.util;

public class SQLUtil {

	public static String getDayOfWeek(int dowNum) {
		switch(dowNum) {
		case 1:
			return "일요일";
		case 2:
			return "월요일";
		case 3:
			return "화요일";
		case 4:
			return "수요일";
		case 5:
			return "목요일";
		case 6:
			return "금요일";
		case 7:
			return "토요일";
		default:
			return "null요일";
		}
	}

}
