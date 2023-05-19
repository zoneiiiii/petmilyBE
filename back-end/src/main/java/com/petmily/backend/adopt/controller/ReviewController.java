package com.petmily.backend.adopt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.adopt.dto.ReviewDto;
import com.petmily.backend.adopt.service.ReviewService;
import com.petmily.backend.support.volunteerReview.dto.VolunteerReviewDto;



@RestController
@RequestMapping("/board/review")
public class ReviewController {

	private final ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	 @PostMapping("/post")
	    public String write(ReviewDto reviewDto) {
		 	reviewService.savePost(reviewDto);
	        return "2";
	    }

}
