package com.petmily.backend.support.volunteer.controller;

import com.petmily.backend.support.volunteer.service.VolunteerService;
import com.petmily.backend.support.volunteer.dto.VolunteerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
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
}
