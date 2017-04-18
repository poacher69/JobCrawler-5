package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public  static String getStringTime(){
		
		LocalDateTime dateTime = LocalDateTime.now();
		String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
		return formattedDateTime;
	}
	
	public static void main(String[] args) {
		System.out.println(getStringTime());
	}
}
