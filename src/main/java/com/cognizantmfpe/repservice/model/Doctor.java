package com.cognizantmfpe.repservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Instantiates a new doctor.
 *
 * @param docId the doc id
 * @param docName the doc name
 * @param docContactNumber the doc contact number
 * @param treatingAilment the treating ailment
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	
	/** The doc id. */
	private int docId;
	
	/** The doc name. */
	private String docName;
	
	/** The doc contact number. */
	private String docContactNumber;
	
	/** The treating ailment. */
	private String treatingAilment;
}
