/**
 * 
 */
package com.company.Personalmgmt.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * This class is used for
 *
 * @author Jayaram
 */

public class CommonUtils {

	public static long calculateTenureInDays(String openingDateStr, String maturityDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate openingDate = LocalDate.parse(openingDateStr, formatter);
		LocalDate maturityDate = LocalDate.parse(maturityDateStr, formatter);
		return ChronoUnit.DAYS.between(openingDate, maturityDate);
	}

}
