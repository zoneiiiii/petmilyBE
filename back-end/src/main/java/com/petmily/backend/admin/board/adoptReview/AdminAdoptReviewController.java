package com.petmily.backend.admin.board.adoptReview;

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

@RequestMapping("/admin/board/adoptReview")
@RequiredArgsConstructor
@RestController
public class AdminAdoptReviewController {
	@Autowired
	private final AdminAdoptReviewService adminAdoptReviewService;
	
	@GetMapping("/list")
	public Page<AdminAdoptReview> getList(
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="20") int limit, 
			@RequestParam(value="search", defaultValue="") String search, 
			@RequestParam(value="search_mode", defaultValue="subject") String search_mode) {
		Page<AdminAdoptReview> reviewList = null;
		try {
			String keyword = URLDecoder.decode(search, "UTF8").replaceAll("&", " ").trim().replaceAll("\\s+", "|");
			reviewList = this.adminAdoptReviewService.getAdminAdoptReviewList(page, limit, keyword, search_mode);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return reviewList;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> deleteAdoptReviewList(@RequestBody Map<String, List<Long>> requestBody) {
		List<Long> boardNums = requestBody.get("boardNums");
		this.adminAdoptReviewService.deleteAdoptReviewList(boardNums);
		return ResponseEntity.ok(true);
	}
}
