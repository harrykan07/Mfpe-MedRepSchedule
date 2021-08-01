package com.cognizantmfpe.repservice.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private String reason;

}