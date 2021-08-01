package com.cognizantmfpe.repservice.util;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringToLocalDateUtil {
	public static LocalDate getDateInLocalDateForm(String startDate) {

		LocalDate localDate = null;
		try {

			log.info("START");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
			localDate = LocalDate.parse(startDate, formatter);

			log.debug("localDate : {}", localDate);

		} catch (Exception e) {
			log.error("DATE FORMAT EXCEPTION");
		}

		log.info("END");

		return localDate;
	}


}
