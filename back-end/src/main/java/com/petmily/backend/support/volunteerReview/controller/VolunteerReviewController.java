package com.petmily.backend.support.volunteerReview.controller;


import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;
import com.petmily.backend.support.volunteerReview.service.VolunteerReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/donate/volunteer/review")
public class VolunteerReviewController {

    private final VolunteerReviewService volunteerReviewService;
    private final HttpSession httpSession;

    public VolunteerReviewController(VolunteerReviewService volunteerReviewService, HttpSession httpSession) {
        this.volunteerReviewService = volunteerReviewService;
        this.httpSession = httpSession;
    }

//    @GetMapping
//    public ResponseEntity<List<VolunteerReviewDto>> getAllVolunteerReviews(){
//        List<VolunteerReviewDto> volunteerReviews = volunteerReviewService.getAllVolunteerReviews();
//        return ResponseEntity.ok(volunteerReviews);
//    }

    @GetMapping
    public ResponseEntity<Page<VolunteerReviewDto>> getAllVolunteerReviews(@RequestParam int page){
        Page<VolunteerReviewDto> volunteerReviews = volunteerReviewService.getAllVolunteerReviews(page);
        return ResponseEntity.ok(volunteerReviews);
    }

    @GetMapping("/{boardNum}")
    public ResponseEntity<VolunteerReviewDto> getVolunteerReviewById(@PathVariable Long boardNum){
        VolunteerReviewDto volunteerReview = volunteerReviewService.getVolunteerReviewById(boardNum);
        return ResponseEntity.ok(volunteerReview);
    }

    @PostMapping("/{boardNum}/increase-viewcount")
    public ResponseEntity<Void> increaseViewCount(@PathVariable Long boardNum){
        volunteerReviewService.increaseViewCount(boardNum);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/write") //게시글 작성
    public ResponseEntity<VolunteerReviewDto> createVolunteerReview(@RequestBody VolunteerReviewDto volunteerReviewDto, HttpSession session){
        String memberId = (String) session.getAttribute("id");
        VolunteerReviewDto createdVolunteerReview = volunteerReviewService.createVolunteerReview(volunteerReviewDto, memberId);
        return ResponseEntity.ok(createdVolunteerReview);
    }

    @DeleteMapping("/{boardNum}") //게시글 삭제
    public ResponseEntity<Void> deleteVolunteerById(@PathVariable Long boardNum){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        volunteerReviewService.deleteVolunteerReviewById(boardNum, loggedInUserId);
        log.info("사용자 {} VolunteerReview 게시판 boardNum {} 삭제 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardNum}") //게시글 수정
    public ResponseEntity<VolunteerReviewDto> updateVolunteerReview(@PathVariable Long boardNum, @RequestBody VolunteerReviewDto volunteerReviewDto){
        String loggedInUserId = (String) httpSession.getAttribute("id");
        VolunteerReviewDto updatedVolunteerReview = volunteerReviewService.updateVolunteerReview(boardNum, volunteerReviewDto, loggedInUserId);
        log.info("사용자 {} VolunteerReview 게시판 boardNum {} 수정 완료 ", loggedInUserId, boardNum);
        return ResponseEntity.ok(updatedVolunteerReview);
    }



}
