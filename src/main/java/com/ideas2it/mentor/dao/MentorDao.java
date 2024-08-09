package com.ideas2it.mentor.dao;

import com.ideas2it.model.Mentor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorDao extends CrudRepository<Mentor, Integer> { }
