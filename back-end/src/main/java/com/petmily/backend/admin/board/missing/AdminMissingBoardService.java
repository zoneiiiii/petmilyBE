package com.petmily.backend.admin.board.missing;

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
public class AdminMissingBoardService {
	private final AdminMissingBoardRepository missingBoardRepository;
	
	public Page<AdminMissingBoard> getAdminMissingBoardList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<AdminMissingBoard> adminAdoptReviewList = null;
		if(keyword.isEmpty()) {
			adminAdoptReviewList = this.missingBoardRepository.findAllAdminMissingBoard(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				adminAdoptReviewList = this.missingBoardRepository.findByBoardSubjectContainingOrBoardContentContaining(keyword, pageable);
				break;
			case "subject":
				adminAdoptReviewList = this.missingBoardRepository.findByBoardSubjectContaining(keyword, pageable);
				break;
			case "content":
				adminAdoptReviewList = this.missingBoardRepository.findByBoardContentContaining(keyword, pageable);
				break;
			}
		}
		return adminAdoptReviewList;
	}
	
	public void deleteMissingBoardList(List<Long> boardNums) {
		this.missingBoardRepository.deleteAllByIdInBatch(boardNums);
	}

}
