package com.cognizantmfpe.repservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Instantiates a new medical representative.
 *
 * @param id the id
 * @param name the name
 * @param phoneNumber the phone number
 */
@Entity
@Data
@Table(name = "medical_representatives")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRepresentative {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int repId;
	
	/** The name. */
	@Column
	private String repName;

	/** The phone number. */
	@Column
	private String phoneNumber;

}