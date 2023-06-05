package com.petmily.backend.admin.board.adoptReview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminAdoptReviewService {
	private final AdminReviewRepository reviewRepository;
	
	public Page<AdminAdoptReview> getAdminAdoptReviewList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<AdminAdoptReview> adminAdoptReviewList = null;
		if(keyword.isEmpty()) {
			adminAdoptReviewList = this.reviewRepository.findAllAdminAdoptReview(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				adminAdoptReviewList = this.reviewRepository.findByReviewSubjectContainingOrReviewContentContaining(keyword, pageable);
				break;
			case "subject":
				adminAdoptReviewList = this.reviewRepository.findByReviewSubjectContaining(keyword, pageable);
				break;
			case "content":
				adminAdoptReviewList = this.reviewRepository.findByReviewContentContaining(keyword, pageable);
				break;
			}
		}
		return adminAdoptReviewList;
	}
	
	public void deleteAdoptReviewList(List<Long> boardNums) {
		this.reviewRepository.deleteAllByIdInBatch(boardNums);
	}
}
