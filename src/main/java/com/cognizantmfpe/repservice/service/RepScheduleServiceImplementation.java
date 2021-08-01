package com.cognizantmfpe.repservice.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizantmfpe.repservice.exception.TokenValidationFailedException;
import com.cognizantmfpe.repservice.feign.AuthenticationFeignClient;
import com.cognizantmfpe.repservice.feign.MedicineStockFeignClient;
import com.cognizantmfpe.repservice.model.Doctor;
import com.cognizantmfpe.repservice.model.JwtResponse;
import com.cognizantmfpe.repservice.model.MedicalRepresentative;
import com.cognizantmfpe.repservice.model.RepSchedule;
import com.cognizantmfpe.repservice.repo.MedicalRepRepo;
import com.cognizantmfpe.repservice.util.ExtractDoctorsUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The Service Class RepScheduleServiceImplementation.
 */
@Service
@Slf4j
public class RepScheduleServiceImplementation implements IRepScheduleService {

	/** The repo. */
	@Autowired
	private MedicalRepRepo mrepo;

	@Autowired
	AuthenticationFeignClient authFeignClient;
	
	/** The medicine stock feign client. */
	@Autowired
	private MedicineStockFeignClient medicineStockFeignClient;

	/**
	 * Gets the rep schedules by date.
	 *
	 * @param date the date
	 * @return the rep schedules by date
	 * @throws TokenValidationFailedException 
	 */
	@Override
	public List<RepSchedule> getRepSchedulesByDate(String token, LocalDate date) throws TokenValidationFailedException {
		
		log.info("START");

		if (!isValidSession(token)) {
			log.info("End");
			
			return null;
		}
		
		List<RepSchedule> finalSchedule = new ArrayList<>();
		
		List<Doctor> doctors =ExtractDoctorsUtil.getDoctors();
		
		
		log.debug("Doctors : {}", doctors);
		
		
		List<MedicalRepresentative> medicalRepresentatives = (List<MedicalRepresentative>) mrepo.findAll();
		int MEDICAL_REPRESENTATIVES = medicalRepresentatives.size(); 
		
		log.debug("MedicalReps : {}", medicalRepresentatives);
		

		LocalDate localDate = date;
		
		LocalTime now = LocalTime.now();
		LocalTime one = LocalTime.of(13, 0);//ONE O'CLOCK

		LocalDate today = LocalDate.now();
		if (date.isBefore(today)) {

			log.error("CANNOT SCHEDULE FOR ANY PREVIOUS DATES");
			log.info("END");
			return finalSchedule;
		}

		if (date.equals(today)) {
			if (now.isAfter(one)) {
				localDate = localDate.plusDays(1);
			}

		}

		for (int i = 0; i < doctors.size(); i++) {

			if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				localDate = localDate.plusDays(1);
			}

			Doctor doctor = doctors.get(i);
			MedicalRepresentative medicalRepresentative = medicalRepresentatives.get(i % MEDICAL_REPRESENTATIVES);//as only 3 reps are avaliable at the moment
			

			
			RepSchedule repSchedule = new RepSchedule();
			repSchedule.setRepId(i+1);
			repSchedule.setRepName(medicalRepresentative.getRepName() );
			repSchedule.setDoctorName(doctor.getDocName());
			repSchedule.setDoctorContact(doctor.getDocContactNumber());
			repSchedule.setDateOfMeeting(localDate);
			repSchedule.setMeetingSlot("1 PM to 2 PM");
			repSchedule.setTargetAilment(doctor.getTreatingAilment());

			String[] medicinesByTreatingAilment = medicineStockFeignClient.getMedicineByAilment(token, doctor.getTreatingAilment());

			repSchedule.setMedicines(medicinesByTreatingAilment);

			log.debug("repSchedule : {}", repSchedule);

			finalSchedule.add(repSchedule);

			localDate = localDate.plusDays(1);
		}

		log.debug("RepSchedule : {}", finalSchedule);

		log.info("END");
		return finalSchedule;
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
