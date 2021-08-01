package com.cognizantmfpe.repservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

/**
 * The Entrypoint : Class MedicalRepresentativeScheduleMicroserviceApplication.
 */
@SpringBootApplication
@EnableFeignClients
@Slf4j
@EnableEurekaClient
public class MedicalRepresentativeScheduleMicroserviceApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		log.info("START");
		SpringApplication.run(MedicalRepresentativeScheduleMicroserviceApplication.class, args);
		log.info("END");
	}

}
