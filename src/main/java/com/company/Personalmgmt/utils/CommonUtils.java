/**
 * 
 */
package com.company.Personalmgmt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * This class is used for
 *
 * @author Jayaram
 */

// Code Reusability ,Performance, Utility Functions

public class CommonUtils {

	public static long calculateTenureInDays(String openingDateStr, String maturityDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate openingDate = LocalDate.parse(openingDateStr, formatter);
		LocalDate maturityDate = LocalDate.parse(maturityDateStr, formatter);
		return ChronoUnit.DAYS.between(openingDate, maturityDate);
	}

	public static LocalDateTime convertStringToDate(String dateString) {

		LocalDateTime dateTime = null;
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			Date date = inputFormat.parse(dateString);
			String formattedDate = outputFormat.format(date);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			dateTime = LocalDateTime.parse(formattedDate, formatter);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTime;
	}

}
