package com.petmily.backend.admin.board.volunteerReview;

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
public class AdminVolunteerReviewService {
	private final AdminVolunteerReviewRepository reviewRepository;
	
	public Page<AdminVolunteerReview> getAdminVolunteerReviewList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<AdminVolunteerReview> adminVolunteerReviewList = null;
		if(keyword.isEmpty()) {
			adminVolunteerReviewList = this.reviewRepository.findAllAdminVolunteerReview(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				adminVolunteerReviewList = this.reviewRepository.findByReviewSubjectContainingOrReviewContentContaining(keyword, pageable);
				break;
			case "subject":
				adminVolunteerReviewList = this.reviewRepository.findByReviewSubjectContaining(keyword, pageable);
				break;
			case "content":
				adminVolunteerReviewList = this.reviewRepository.findByReviewContentContaining(keyword, pageable);
				break;
			}
		}
		return adminVolunteerReviewList;
	}
	
	public void deleteVolunteerReviewList(List<Long> boardNums) {
		this.reviewRepository.deleteAllByIdInBatch(boardNums);
	}
}
