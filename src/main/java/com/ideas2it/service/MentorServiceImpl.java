package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.dao.MentorDao;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Mentor;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.dto.MentorDto;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.mapper.MentorMapper;

@Service
public class MentorServiceImpl implements MentorService {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private MentorDao mentorDao;

    public MentorDto addMentor(MentorDto mentorDto) {
        Mentor mentor = MentorMapper.convertToEntity(mentorDto);
        return MentorMapper.convertToDto(mentorDao.save(mentor));
    }

    public void deleteMentor(int mentorId) {
        Mentor mentor = mentorDao.findByIsDeletedFalseAndMentorId(mentorId);
        mentor.setDeleted(true);
        mentorDao.save(mentor);
    }

    public MentorDto getMentorById(int mentorId) {
        Mentor mentor = mentorDao.findByIsDeletedFalseAndMentorId(mentorId);
        if (null == mentor) {
            logger.warn("Mentor Not found with Id :{}", mentorId);
            throw new NoSuchElementException("Mentor Not found with Id :" + mentorId);
        } else {
            return MentorMapper.convertToDto(mentor);
        }
    }

    public List<EmployeeDto> getEmployeesByMentorId(int mentorId) {
        Mentor mentor = mentorDao.findByIsDeletedFalseAndMentorId(mentorId);
        List<EmployeeDto> presentEmployee = new ArrayList<>();
        for(Employee employee : mentor.getEmployees()) {
            if (!employee.isDeleted()) {
                presentEmployee.add(EmployeeMapper.convertEntityToDto(employee));
            }
        }
        return presentEmployee;
    }
}
