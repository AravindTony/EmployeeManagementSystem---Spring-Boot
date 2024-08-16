package com.ideas2it.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.MentorDto;
import com.ideas2it.service.MentorService;

/** 
 * <p>
 * This class used to get inputs and display
 * the information about the Mentor
 * like Mentor name and Id 
 * </p>
 * @author Aravind
 */
@RestController
@RequestMapping ("api/v1/mentors")
public class MentorController {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private MentorService mentorService;

    /** 
     * <p>
     * This Method is to get the Choice of Mentor Services
     * 1. Add the Mentor to the Database
     * 2. Display the Employees by the Mentor 
     * </p>
     */
	@PostMapping
    public ResponseEntity<MentorDto> addMentor(@Valid @RequestBody MentorDto mentorDto) {
		logger.debug("Mentor adding Process Started..");
		return new ResponseEntity<>(mentorService.addMentor(mentorDto), HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMentor(@PathVariable("id") int mentorId) {
		mentorService.deleteMentor(mentorId);
		logger.info("Mentor Deleted Successfully..");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method return the Employees by Mentor ID
	 * @param mentorId - Mentor Id
	 * @return EmployeeDto - List of Employees as Dto
	 */
	@GetMapping("/employees/{mentorId}")
	public ResponseEntity<List<EmployeeDto>> displayEmployeesByMentorId(@PathVariable int mentorId) {
		List<EmployeeDto> employees = mentorService.getEmployeesByMentorId(mentorId);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
}