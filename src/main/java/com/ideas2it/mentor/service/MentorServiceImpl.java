package com.ideas2it.mentor.service;

import com.ideas2it.mentor.dao.MentorDao;
import com.ideas2it.model.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    private MentorDao mentorDao;

    public Mentor addMentor(Mentor mentor) {
        return mentorDao.save(mentor);
    }

    public void deleteMentor(int mentorId) {
        Mentor mentor = mentorDao.findById(mentorId)
                .orElseThrow(() -> new IllegalArgumentException("Mentor not found with ID: " + mentorId));
        mentor.setIsDeleted(true);
        mentorDao.save(mentor);
    }
}
