package com.petmily.backend.support.volunteer.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.support.volunteer.dto.VolunteerDto;
import com.petmily.backend.support.volunteer.service.VolunteerService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/board/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;
    private final HttpSession httpSession;

    public VolunteerController(VolunteerService volunteerService, HttpSession httpSession) {
        this.volunteerService = volunteerService;
        this.httpSession = httpSession;
    }

//    @GetMapping
//    public ResponseEntity<List<VolunteerDto>> getAllVolunteers() {
//        List<VolunteerDto> volunteers = volunteerService.getAllVolunteers();
//        return ResponseEntity.ok(volunteers);
//    }

    @GetMapping
    public ResponseEntity<Page<VolunteerDto>> getAllVolunteers(@RequestParam int page){
        Page<VolunteerDto> volunteers = volunteerService.getAllVolunteers(page);
        return ResponseEntity.ok(volunteers);
    }

    @GetMapping("/{boardNum}") //게시글 상세
    public ResponseEntity<VolunteerDto> getVolunteerById(@PathVariable Long boardNum) {
        VolunteerDto volunteer = volunteerService.getVolunteerById(boardNum);
        return ResponseEntity.ok(volunteer);
    }

    @PostMapping("/{boardNum}/increase-viewcount") // 조회수 증가
    public ResponseEntity<Void> increaseViewCount(@PathVariable Long boardNum) {
        volunteerService.increaseViewCount(boardNum);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/write") //게시글 작성
    public ResponseEntity<VolunteerDto> createVolunteer(@RequestBody VolunteerDto volunteerDto, HttpSession session){
        System.out.println(session.getAttribute("id"));
        String memberId = (String)session.getAttribute("id");
        VolunteerDto createdVolunteer = volunteerService.createVolunteer(volunteerDto, memberId);
        return ResponseEntity.ok(createdVolunteer);
    }

    @DeleteMapping("/{boardNum}") //게시글 삭제
    public ResponseEntity<Void> deleteVolunteerById(@PathVariable Long boardNum){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        volunteerService.deleteVolunteerById(boardNum, loggedInUserId);
        log.info("사용자 {} boardNum {} 삭제 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardNum}") //게시글 수정
    public ResponseEntity<VolunteerDto> updateVolunteer(@PathVariable Long boardNum, @RequestBody VolunteerDto volunteerDto){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        VolunteerDto updatedVolunteer = volunteerService.updateVolunteer(boardNum, volunteerDto, loggedInUserId);
        log.info("사용자 {} boardNum {} 수정 완료 ", loggedInUserId, boardNum);
        return  ResponseEntity.ok(updatedVolunteer);
    }


}
