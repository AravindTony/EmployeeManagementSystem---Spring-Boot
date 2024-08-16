package com.ideas2it.mapper;

import com.ideas2it.dto.MentorDto;
import com.ideas2it.model.Mentor;

public class MentorMapper {

    public static MentorDto convertToDto(Mentor mentor) {
        return new MentorDto(mentor.getMentorId(), mentor.getMentorName());
    }

    public static Mentor convertToEntity(MentorDto mentorDto) {
        Mentor mentor = new Mentor();
        mentor.setMentorId(mentorDto.getId());
        mentor.setMentorName(mentorDto.getName());
        return mentor;
    }
}
