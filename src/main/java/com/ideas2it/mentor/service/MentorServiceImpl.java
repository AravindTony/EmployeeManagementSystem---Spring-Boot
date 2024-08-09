package com.ideas2it.mentor.service;

import com.ideas2it.mentor.dao.MentorDao;
import com.ideas2it.mentor.dto.MentorDto;
import com.ideas2it.mentor.mentorMapping.MentorMapper;
import com.ideas2it.model.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    private MentorDao mentorDao;



    public MentorDto addMentor(MentorDto mentorDto) {
        Mentor mentor = MentorMapper.convertToEntity(mentorDto);
        Mentor savedMentor= mentorDao.save(mentor);
        return MentorMapper.convertToDto(savedMentor);
    }

    public void deleteMentor(int mentorId) {
        mentorDao.deleteById(mentorId);
    }
}
