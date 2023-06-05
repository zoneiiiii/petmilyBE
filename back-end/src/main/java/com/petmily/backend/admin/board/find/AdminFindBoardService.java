package com.petmily.backend.admin.board.find;

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
public class AdminFindBoardService {
	private final AdminFindBoardRepository findBoardRepository;
	
	public Page<AdminFindBoard> getAdminFindBoardList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<AdminFindBoard> adminAdoptReviewList = null;
		if(keyword.isEmpty()) {
			adminAdoptReviewList = this.findBoardRepository.findAllAdminFindBoard(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				adminAdoptReviewList = this.findBoardRepository.findByBoardSubjectContainingOrBoardContentContaining(keyword, pageable);
				break;
			case "subject":
				adminAdoptReviewList = this.findBoardRepository.findByBoardSubjectContaining(keyword, pageable);
				break;
			case "content":
				adminAdoptReviewList = this.findBoardRepository.findByBoardContentContaining(keyword, pageable);
				break;
			}
		}
		return adminAdoptReviewList;
	}
	
	public void deleteFindBoardList(List<Long> boardNums) {
		this.findBoardRepository.deleteAllByIdInBatch(boardNums);
	}

}
