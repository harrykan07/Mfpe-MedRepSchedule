package com.cognizantmfpe.repservice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Instantiates a new rep schedule response full.
 *
 * @param repId         the rep id
 * @param repName       the rep name
 * @param docName       the doc name
 * @param targetAilment the target ailment
 * @param medicines     the medicines
 * @param meetingSlot   the meeting slot
 * @param dateOfMeeting the date of meeting
 * @param docContact    the doc contact
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepSchedule{

	/** The rep id. */
	@Id
	@GeneratedValue
	private Integer repId;

	/** The rep name. */
	@Column
	private String repName;

	/** The doc name. */
	@Column
	private String doctorName;

	/** The target ailment. */
	@Column
	private String targetAilment;

	/** The medicines. */
	private String[] medicines;

	/** The meeting slot. */
	@Column
	private String meetingSlot;

	/** The date of meeting. */
	@Column
	private LocalDate dateOfMeeting;

	/** The doc contact. */
	@Column
	private String doctorContact;


}
