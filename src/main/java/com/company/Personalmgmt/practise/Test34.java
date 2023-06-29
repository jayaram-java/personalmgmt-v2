package com.company.Personalmgmt.practise;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Test34 {

	public static void main(String[] args) {
		
		
		
		String path = "D:/2021/upload/Government/14/Aadhaar.pdf/Aadhaar.pdf";
		
		String output = path.replace('/', '\\');
		
		System.out.println(output);


	}

	void previousmonth() {

		//

		String input = "2022-01-22T23:50";

		String result = input.replace("T", " ");

		// System.out.println(result);

		String dateStart = "1996-10-05";
		String dateStop = "1996-12-03";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			Instant instant1 = d1.toInstant();

			ZonedDateTime zdt1 = instant1.atZone(ZoneId.systemDefault());

			LocalDate date1 = zdt1.toLocalDate();

			Instant instant2 = d2.toInstant();

			ZonedDateTime zdt2 = instant2.atZone(ZoneId.systemDefault());

			LocalDate date2 = zdt2.toLocalDate();

			Period diff1 = Period.between(date1, date2);

			int month = diff1.getMonths();

			int days = diff1.getDays();

			int year = diff1.getYears();

			System.out.println(" days : " + days + " month : " + month + " year : " + year);

			// in milliseconds
			long diff = d2.getTime() - d1.getTime();

			// long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			// System.out.print(diffDays + " days, ");
			// System.out.print(diffHours + " hours, ");
			// System.out.print(diffMinutes + " minutes, ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
