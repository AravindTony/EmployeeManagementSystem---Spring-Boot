package com.ideas2it.service;

import java.util.List;

import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.MentorDto;
import org.springframework.stereotype.Service;

import com.ideas2it.model.Mentor;

@Service
public interface MentorService {

    /**
     * <p>
     * This method return mentor object as argument to
     * mentor Dao
     * </p>
     * @param mentorDto - Mentor Dto Object
     * @return MentorDto {@link com.ideas2it.dto.MentorDto}- Mentor as Dto Object
     */
    MentorDto addMentor(MentorDto mentorDto);

    /**
     * <p>
     * This method to delete a mentor by the mentor id
     * given by the user
     * </p>
     * @param mentorId - Mentor ID given by the user
     *
     */
    void deleteMentor(int mentorId);

    /**
     * This method return the Mentor by ID
     * @param mentorId - Mentor ID
     * @return Mentor as Object
     */
    MentorDto getMentorById(int mentorId);

    /**
     * This method return the Employees by Mentor ID
     * @param mentorId - Mentor Id
     * @return List<Employee> - List of Employees
     */
    List<EmployeeDto> getEmployeesByMentorId(int mentorId);
}
