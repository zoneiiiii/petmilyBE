package com.petmily.backend.adopt.adoptReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("/board/review")
public class ReviewController {
	private final ReviewService reviewService;
	private final HttpSession httpSession;

	@Autowired
	public ReviewController(ReviewService reviewService, HttpSession httpSession) {
		this.reviewService = reviewService;
		this.httpSession = httpSession;
	}
	
	@GetMapping("/{boardNum}")
    public ReviewBoard getBoard(@PathVariable Long boardNum) {
        return reviewService.getReviewBoard(boardNum);
    }
	
//	@PostMapping("/insert")
//	public void writeReview(@RequestBody ReviewBoard review, HttpSession session) {
//		System.out.println(session.getAttribute("id"));
//		String memberId = (String)session.getAttribute("id");
//		reviewService.writeReview(review);	 
//	}
	@PostMapping("/insert")
    public ResponseEntity<ReviewDto> writeReview(@RequestBody ReviewDto reviewDto, HttpSession session){
        System.out.println(session.getAttribute("id"));
        String memberId = (String)session.getAttribute("id");
        ReviewDto writeReview = reviewService.writeReview(reviewDto, memberId);
        return ResponseEntity.ok(writeReview);
    }
	 
	@GetMapping("/list")
	public List<ReviewBoardList> boardList(Model model, HttpSession session){
//		String memberId = (String)session.getAttribute("id");
		List<ReviewBoardList> reviewList = reviewService.reviewList();
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
	
	@GetMapping("/search")
	public String search(String keyword, Model model) {
		List<ReviewBoardList> searchList = reviewService.search(keyword);
	 
		model.addAttribute("searchList", searchList);
	 
		return "search";
	}
}
