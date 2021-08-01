package com.cognizantmfpe.repservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizantmfpe.repservice.model.MedicalRepresentative;

@Repository
public interface MedicalRepRepo extends CrudRepository<MedicalRepresentative, Integer> {

}
