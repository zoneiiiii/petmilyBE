package com.petmily.backend.support.volunteer.service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.service.MemberService;
import com.petmily.backend.support.volunteer.domain.Volunteer;
import com.petmily.backend.support.volunteer.dto.VolunteerDto;
import com.petmily.backend.support.volunteer.repository.VolunteerRepository;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private MemberService memberService;

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

    public VolunteerDto createVolunteer(VolunteerDto volunteerDto, String memberId){
        Member member = memberService.getMember(memberId);
        Volunteer volunteer = new Volunteer();

        volunteer.setMemberNum(member.getMemberNum());
        volunteer.setBoardId("volunteer");
        volunteer.setShelterName(volunteerDto.getShelterName());
        volunteer.setVolunteerNumber(volunteerDto.getVolunteerNumber());
        volunteer.setVolunteerAge(volunteerDto.getVolunteerAge());
        volunteer.setVolunteerAddr(volunteerDto.getVolunteerAddr());
        volunteer.setVolunteerAddrDetail(volunteerDto.getVolunteerAddrDetail());
        volunteer.setVolunteerSubject(volunteerDto.getVolunteerSubject());
        volunteer.setVolunteerContent(volunteerDto.getVolunteerContent());
        volunteer.setVolunteerCount(0);
        volunteer.setVolunteerDate(volunteerDto.getVolunteerDate());
        volunteer.setImgThumbnail(volunteerDto.getImgThumbnail());
        volunteer.setVolunteerStartPeriod(volunteerDto.getVolunteerStartPeriod());
        volunteer.setVolunteerEndPeriod(volunteerDto.getVolunteerEndPeriod());
        volunteer.setVolunteerStatus(volunteerDto.getVolunteerStatus());

        volunteerRepository.save(volunteer);

        return convertToDto(volunteer);
    }

    public void deleteVoulunteerById(Long boardNum, String loggedInUserId){
        Member member = memberService.getMember(loggedInUserId); //로그인한 사용자 정보 가져오기
        Volunteer volunteer = volunteerRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
        if (!member.getMemberNum().equals(volunteer.getMemberNum())) {
            throw new AccessDeniedException("해당 게시글을 삭제할 권한이 없습니다.");
        }
        volunteerRepository.delete(volunteer);
    }

    public VolunteerDto updateVolunteer(Long boardNum, VolunteerDto volunteerDto, String loggedInUserId){
        Member member = memberService.getMember(loggedInUserId);
        Volunteer volunteer = volunteerRepository.findById(boardNum)
                .orElseThrow(() -> new NoSuchElementException("해당 boardNum을 찾을 수 없습니다." + boardNum));
        if(!member.getMemberNum().equals(volunteer.getMemberNum())){
            throw new AccessDeniedException("해당 게시글을 수정할 권한이 없습니다.");
        }
        System.out.println(volunteerDto);
        volunteer.setShelterName(volunteerDto.getShelterName());
        volunteer.setVolunteerNumber(volunteerDto.getVolunteerNumber());
        volunteer.setVolunteerAge(volunteerDto.getVolunteerAge());
        volunteer.setVolunteerAddr(volunteerDto.getVolunteerAddr());
        volunteer.setVolunteerAddrDetail(volunteerDto.getVolunteerAddrDetail());
        volunteer.setVolunteerSubject(volunteerDto.getVolunteerSubject());
        volunteer.setVolunteerContent(volunteerDto.getVolunteerContent());
//        volunteer.setVolunteerDate(volunteerDto.getVolunteerDate());
        volunteer.setImgThumbnail(volunteerDto.getImgThumbnail());
        volunteer.setVolunteerStartPeriod(volunteerDto.getVolunteerStartPeriod());
        volunteer.setVolunteerEndPeriod(volunteerDto.getVolunteerEndPeriod());
        volunteer.setVolunteerStatus(volunteerDto.getVolunteerStatus());

        volunteerRepository.save(volunteer);

        return convertToDto(volunteer);
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
