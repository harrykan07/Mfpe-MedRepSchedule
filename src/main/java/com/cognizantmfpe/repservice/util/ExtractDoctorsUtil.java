package com.cognizantmfpe.repservice.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.cognizantmfpe.repservice.model.Doctor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtractDoctorsUtil {
	
	public static List<Doctor> getDoctors()
	{
		log.info("START");
		List<Doctor> doctorList = new ArrayList<>();
		Resource resource = new ClassPathResource("/static/DoctorDetails.txt");
		try {
			InputStream inputStream = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String reg = br.readLine();
			while(reg!=null)
			{
				String[] line = reg.split("\\|");
	
				Doctor doctor = new Doctor(Integer.parseInt(line[0]), line[1], line[2], line[3]);
				
				doctorList.add(doctor);
				reg = br.readLine();
			}
		} catch (IOException e) {
			log.error("FILE NOT FOUND");
		}
		
		log.debug("doctorsList : {}", doctorList);

		log.info("END");

		return doctorList;
		
	}
	
}
