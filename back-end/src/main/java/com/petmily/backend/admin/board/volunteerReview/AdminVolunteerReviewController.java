package com.petmily.backend.admin.board.volunteerReview;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin/board/volunteerReview")
@RequiredArgsConstructor
@RestController
public class AdminVolunteerReviewController {
	@Autowired
	private final AdminVolunteerReviewService adminVolunteerReviewService;
	
	@GetMapping("/list")
	public Page<AdminVolunteerReview> getList(
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="20") int limit, 
			@RequestParam(value="search", defaultValue="") String search, 
			@RequestParam(value="search_mode", defaultValue="subject") String search_mode) {
		Page<AdminVolunteerReview> reviewList = null;
		try {
			String keyword = URLDecoder.decode(search, "UTF8").replaceAll("&", " ").trim().replaceAll("\\s+", "|");
			reviewList = this.adminVolunteerReviewService.getAdminVolunteerReviewList(page, limit, keyword, search_mode);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reviewList;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> deleteVolunteerReviewList(@RequestBody Map<String, List<Long>> requestBody) {
		List<Long> boardNums = requestBody.get("boardNums");
		this.adminVolunteerReviewService.deleteVolunteerReviewList(boardNums);
		return ResponseEntity.ok(true);
	}
}
