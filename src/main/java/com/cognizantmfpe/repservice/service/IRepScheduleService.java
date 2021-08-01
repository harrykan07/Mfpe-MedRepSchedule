package com.cognizantmfpe.repservice.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.cognizantmfpe.repservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.repservice.model.RepSchedule;

/**
 * The Interface IRepScheduleService.
 */
public interface IRepScheduleService {

	/**
	 * Gets the rep schedules by date.
	 *
	 * @param date the date
	 * @return the rep schedules by date
	 * @throws TokenValidationFailedException 
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<RepSchedule> getRepSchedulesByDate(String token, LocalDate date) throws TokenValidationFailedException;

}
