package com.petmily.backend.adopt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.adopt.domain.ReviewBoard;
import com.petmily.backend.adopt.service.ReviewService;



@RestController
@RequestMapping("/board/review")
public class ReviewController {
	private final ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@PostMapping("/insert")
	public void writeReview(@RequestBody ReviewBoard review) {
		reviewService.writeReview(review);
		 System.out.println("입력완료");
		 
	}
	 
	@GetMapping("/list")
	public List<ReviewBoard> boardList(Model model){
		List<ReviewBoard> reviewList = reviewService.reviewList();
	    model.addAttribute("list",reviewList);
	    return reviewList;
	}
	
	@DeleteMapping("/{boardNum}")
	public void deleteAllByBoardNum(@PathVariable Long boardNum){
		reviewService.deleteAllByBoardNum(boardNum);
		System.out.println("완료");
	}
	  
	@PutMapping("/{boardNum}")
	public void updateReview(@PathVariable Long boardNum, @RequestBody ReviewBoard review) {
		reviewService.updateReview(boardNum, review);
	}
}
