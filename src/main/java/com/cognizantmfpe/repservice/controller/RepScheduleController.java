package com.cognizantmfpe.repservice.controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizantmfpe.repservice.exception.InvalidDateException;
import com.cognizantmfpe.repservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.repservice.feign.AuthenticationFeignClient;
import com.cognizantmfpe.repservice.model.JwtResponse;
import com.cognizantmfpe.repservice.model.RepSchedule;
import com.cognizantmfpe.repservice.service.RepScheduleServiceImplementation;
import com.cognizantmfpe.repservice.util.StringToLocalDateUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class RepScheduleController.
 */
@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping(value="schedule")
public class RepScheduleController {

	/** The rservice. */
	@Autowired
	private RepScheduleServiceImplementation rservice;
	
	
	@Autowired
	private AuthenticationFeignClient authFeignClient;

	/**
	 * Gets the rep schedule by date.
	 *
	 * @param startDate the start date
	 * @return the rep schedule by date
	 * @throws InvalidDateException 
	 * @throws TokenValidationFailedException 
	 * @throws FileNotFoundException the file not found exception
	 */
	/*
	 * http://localhost:8081/RepSchedule/23-Jun-2021
	 */
	@GetMapping("/RepSchedule/{startDate}")
	public ResponseEntity<List<RepSchedule>> getRepScheduleByDate(@RequestHeader(name = "Authorization") final String token,@PathVariable String startDate) throws InvalidDateException, TokenValidationFailedException {
		log.info("Start");
		
		LocalDate localDate = StringToLocalDateUtil.getDateInLocalDateForm(startDate);
		
		if (!isValidSession(token)) {
			log.info("End");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		if (localDate == null) {
			log.info("End");
			throw new InvalidDateException("Invalid date");
		}
		
		
		List<RepSchedule> repSchedules = rservice.getRepSchedulesByDate(token, localDate);
		log.debug("RepSchedule : {}", repSchedules);

		if (repSchedules.isEmpty()) {
			log.info("End");

			return ResponseEntity.notFound().build();
		}

		log.info("End");

		return ResponseEntity.ok(repSchedules);
	}
	
	
	public Boolean isValidSession(String token) throws TokenValidationFailedException{
		log.info("Start");

		final JwtResponse response = authFeignClient.verifyToken(token);

		log.debug("response : {}", response);

		if (!response.isValid()) {
			log.info("End");

			throw new TokenValidationFailedException("Invalid Token");
		}

		log.info("End");

		return true;
	}
}
