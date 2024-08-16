package com.ideas2it.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.model.Mentor;

/**
 * <p>
 * This interface handles mentor CRUD Operations
 * extends CRUD Repository
 * </p>
 */
@Repository
public interface MentorDao extends CrudRepository<Mentor, Integer> {

    /**
     * This method Return Specific Mentor with MentorId
     * if Mentor is Not Deleted
     * @param mentorId - Mentor ID to Identify
     * @return - Mentor as Object
     */
    Mentor findByIsDeletedFalseAndMentorId(int mentorId);
}
