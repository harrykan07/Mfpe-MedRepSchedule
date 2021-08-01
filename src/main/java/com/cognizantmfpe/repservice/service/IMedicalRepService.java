package com.cognizantmfpe.repservice.service;

import java.util.List;

import com.cognizantmfpe.repservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.repservice.model.MedicalRepresentative;

public interface IMedicalRepService {

	public List<MedicalRepresentative> getAllReps(String token) throws TokenValidationFailedException;

}
