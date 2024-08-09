package com.ideas2it.mentor.controller;

import com.ideas2it.mentor.dto.MentorDto;
import com.ideas2it.mentor.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/** 
 * <p>
 * This class used to get inputs and display
 * the information about the Mentor
 * like Mentor name and Id 
 * </p>
 * @author Aravind
 */
@RestController
@RequestMapping ("api/mentor")
public class MentorController {

	@Autowired
	private MentorService mentorService;

    /** 
     * <p>
     * This Method is to get the Choice of Mentor Services
     * 1. Add the Mentor to the Database
     * 2. Display the Employees by the Mentor 
     * </p>
     */
	@PostMapping("")
    public MentorDto addMentor(@RequestBody MentorDto mentorDto) {
		return mentorService.addMentor(mentorDto);
	}

    @DeleteMapping("{id}")
	public void deleteMentor(@PathVariable("id") int mentorId) {
		mentorService.deleteMentor(mentorId);
	}
}