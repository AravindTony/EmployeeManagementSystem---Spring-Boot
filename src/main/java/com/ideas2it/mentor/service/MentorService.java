package com.ideas2it.mentor.service;

import com.ideas2it.model.Mentor;
import org.springframework.stereotype.Service;

@Service
public interface MentorService {

    /**
     * <p>
     * This method return mentor object as argument to
     * mentor Dao
     * </p>
     * @param mentor - Mentor Dto Object
     *
     */
    Mentor addMentor(Mentor mentor);

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
