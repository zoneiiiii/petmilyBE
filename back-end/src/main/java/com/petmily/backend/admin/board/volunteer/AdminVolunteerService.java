package com.petmily.backend.admin.board.volunteer;

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
public class AdminVolunteerService {
	private final AdminVolunteerRepository reviewRepository;
	
	public Page<AdminVolunteer> getAdminVolunteerList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<AdminVolunteer> adminVolunteerList = null;
		if(keyword.isEmpty()) {
			adminVolunteerList = this.reviewRepository.findAllAdminVolunteer(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				adminVolunteerList = this.reviewRepository.findByVolunteerSubjectContainingOrVolunteerContentContaining(keyword, pageable);
				break;
			case "subject":
				adminVolunteerList = this.reviewRepository.findByVolunteerSubjectContaining(keyword, pageable);
				break;
			case "content":
				adminVolunteerList = this.reviewRepository.findByVolunteerContentContaining(keyword, pageable);
				break;
			}
		}
		return adminVolunteerList;
	}
	
	public void deleteVolunteerList(List<Long> boardNums) {
		this.reviewRepository.deleteAllByIdInBatch(boardNums);
	}
}
