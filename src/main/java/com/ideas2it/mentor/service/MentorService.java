package com.ideas2it.mentor.service;

import com.ideas2it.mentor.dto.MentorDto;
import com.ideas2it.model.Mentor;
import org.springframework.stereotype.Service;

@Service
public interface MentorService {

    /**
     * <p>
     * This method return mentor object as argument to
     * mentor Dao
     * </p>
     * @param mentorDto - Mentor Dto Object
     *
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
}
