package com.cognizantmfpe.repservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizantmfpe.repservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.repservice.feign.AuthenticationFeignClient;
import com.cognizantmfpe.repservice.model.JwtResponse;
import com.cognizantmfpe.repservice.model.MedicalRepresentative;
import com.cognizantmfpe.repservice.repo.MedicalRepRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MedicalRepServiceImpl implements IMedicalRepService {

	@Autowired
	private MedicalRepRepo mrepo;
	
	@Autowired
	AuthenticationFeignClient authFeignClient;
	
	@Override
	public List<MedicalRepresentative> getAllReps(String token) throws TokenValidationFailedException {
		log.info("START");
		if(!isValidSession(token))
		{
			log.info("END");
			return null;
		}
		log.info("END");
		return (List<MedicalRepresentative>) mrepo.findAll();
	}
	
	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		
		log.info("Start");
		
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			log.info("End");

			throw new TokenValidationFailedException("Invalid Token");
		}

		log.info("End");
		return true;
	}

}
