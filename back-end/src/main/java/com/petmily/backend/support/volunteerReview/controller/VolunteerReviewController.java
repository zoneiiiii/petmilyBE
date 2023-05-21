package com.petmily.backend.support.volunteerReview.controller;


import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;
import com.petmily.backend.support.volunteerReview.service.VolunteerReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donate/volunteer/review")
public class VolunteerReviewController {

    private final VolunteerReviewService volunteerReviewService;

    @Autowired
    public VolunteerReviewController(VolunteerReviewService volunteerReviewService) {
        this.volunteerReviewService = volunteerReviewService;
    }

    @GetMapping
    public ResponseEntity<List<VolunteerReviewDto>> getAllVolunteerReviews(){
        List<VolunteerReviewDto> volunteerReviews = volunteerReviewService.getAllVolunteerReviews();

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

}
