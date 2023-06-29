package com.company.Personalmgmt.practise;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import com.company.Personalmgmt.serviceimpl.GeneralServiceImpl;

public class Test {

	public static void main(String[] args) {

		// 2023-05-20T08:10 2023-07-06T08:10

		String ds = "2023-05-20";
		String de = "2023-07-06";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate date5 = LocalDate.parse(ds, dtf);
		LocalDate date6 = LocalDate.parse(de, dtf);

		long daysBetween = Duration.between(date5, date6).toDays();

		System.out.println("Days between = " + daysBetween);

	}

	public void go() {

		String username = "Ram";

		String body = "Dear " + username + "," + '\n'
				+ "  Thank you for using this application. Your expense details are attached below." + '\n' + '\n'
				+ "Regards," + '\n' + "Jayaram";

		// System.out.println(body);

		LocalDateTime localDateTime = LocalDateTime.now();

		String month = localDateTime.getMonth().toString();

		System.out.println(month);

	}

	public static void timeDiff() {

		Date date = new Date();

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		TimeZone tz = TimeZone.getTimeZone("Europe/London");

		df.setTimeZone(tz);

		format.setTimeZone(tz);

		String startDate = format.format(date);

		System.out.println(df.format(date));

		TimeZone tz1 = TimeZone.getTimeZone("Asia/Kolkata");

		df.setTimeZone(tz1);

		format.setTimeZone(tz1);

		String endDate = format.format(date);

		System.out.println(format.format(date));

		GeneralServiceImpl ob = new GeneralServiceImpl();

		String output = ob.timeDifference1(startDate, endDate);

		System.out.println(output);

		// Asia/Shanghai

		// Asia/Kolkata

		// America/New_York

		// Europe/London

	}

	public static String timeDifference1(String startDate, String endDate) {

		String result = "";
		String ds = startDate;
		String de = endDate;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date d1 = null;
		Date d2 = null;

		try {

			d1 = format.parse(ds);
			d2 = format.parse(de);

			long diff = d2.getTime() - d1.getTime();

			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

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

			if (month == 0 && year == 0) {
				result = String.valueOf(diffDays).concat(" Days ").concat(String.valueOf(diffHours)).concat(" Hours ")
						.concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			} else if (year == 0 && month != 0) {
				result = String.valueOf(month).concat(" Month ").concat(String.valueOf(days)).concat(" Days ")
						.concat(String.valueOf(diffHours)).concat(" Hours ").concat(String.valueOf(diffMinutes))
						.concat(" Minutes ");
			} else if (year != 0) {
				result = String.valueOf(year).concat(" Year ").concat(String.valueOf(month)).concat(" Month ")
						.concat(String.valueOf(days)).concat(" Days ").concat(String.valueOf(diffHours))
						.concat(" Hours ").concat(String.valueOf(diffMinutes)).concat(" Minutes ");
			}

		} catch (Exception e) {

		}

		return result;
	}

}
