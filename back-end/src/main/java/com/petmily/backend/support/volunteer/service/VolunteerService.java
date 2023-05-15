package com.petmily.backend.support.volunteer.service;


import com.petmily.backend.support.volunteer.domain.Volunteer;
import com.petmily.backend.support.volunteer.dto.VolunteerDto;
import com.petmily.backend.support.volunteer.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public List<VolunteerDto> getAllVolunteers(){
        List<Volunteer> volunteers = volunteerRepository.findAll(Sort.by(Sort.Direction.DESC, "volunteerDate"));

        return volunteers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public VolunteerDto getVolunteerById(Long boardNum) {
        Volunteer volunteer = volunteerRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("No volunteer found with boardNum: " + boardNum));

        return convertToDto(volunteer);
    }

    public void increaseViewCount(Long boardNum) {
        Volunteer volunteer = volunteerRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("No volunteer found with boardNum: " + boardNum));
        volunteer.setVolunteerCount(volunteer.getVolunteerCount() + 1);
        volunteerRepository.save(volunteer);
    }

    private VolunteerDto convertToDto(Volunteer volunteer) {

        VolunteerDto volunteerDto = new VolunteerDto();
        volunteerDto.setBoardNum(volunteer.getBoardNum());
        volunteerDto.setMemberNum(volunteer.getMemberNum());
        volunteerDto.setBoardId(volunteer.getBoardId());
        volunteerDto.setShelterName(volunteer.getShelterName());
        volunteerDto.setVolunteerNumber(volunteer.getVolunteerNumber());
        volunteerDto.setVolunteerAge(volunteer.getVolunteerAge());
        volunteerDto.setVolunteerAddr(volunteer.getVolunteerAddr());
        volunteerDto.setVolunteerAddrDetail(volunteer.getVolunteerAddrDetail());
        volunteerDto.setVolunteerSubject(volunteer.getVolunteerSubject());
        volunteerDto.setVolunteerContent(volunteer.getVolunteerContent());
        volunteerDto.setVolunteerCount(volunteer.getVolunteerCount());
        volunteerDto.setVolunteerDate(volunteer.getVolunteerDate());
        volunteerDto.setImgThumbnail(volunteer.getImgThumbnail());
        volunteerDto.setVolunteerStartPeriod(volunteer.getVolunteerStartPeriod());
        volunteerDto.setVolunteerEndPeriod(volunteer.getVolunteerEndPeriod());
        volunteerDto.setVolunteerStatus(volunteer.getVolunteerStatus());

        return volunteerDto;
    }
}
