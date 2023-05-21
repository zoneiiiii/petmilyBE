package com.petmily.backend.support.volunteer.controller;

import com.petmily.backend.support.volunteer.service.VolunteerService;
import com.petmily.backend.support.volunteer.dto.VolunteerDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<VolunteerDto>> getAllVolunteers() {
        List<VolunteerDto> volunteers = volunteerService.getAllVolunteers();
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
        volunteerService.deleteVoulunteerById(boardNum, loggedInUserId);
        log.info("사용자 {} boardNum {} 삭제 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok().build();
    }

}
