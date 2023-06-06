package com.petmily.backend.admin.board.flea;

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
public class AdminFleaBoardService {
	private final AdminFleaBoardRepository fleaBoardRepository;
	
	public Page<AdminFleaBoard> getAdminFleaBoardList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<AdminFleaBoard> adminAdoptReviewList = null;
		if(keyword.isEmpty()) {
			adminAdoptReviewList = this.fleaBoardRepository.findAllAdminFleaBoard(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				adminAdoptReviewList = this.fleaBoardRepository.findByBoardSubjectContainingOrBoardContentContaining(keyword, pageable);
				break;
			case "subject":
				adminAdoptReviewList = this.fleaBoardRepository.findByBoardSubjectContaining(keyword, pageable);
				break;
			case "content":
				adminAdoptReviewList = this.fleaBoardRepository.findByBoardContentContaining(keyword, pageable);
				break;
			}
		}
		return adminAdoptReviewList;
	}
	
	public void deleteFleaBoardList(List<Long> boardNums) {
		this.fleaBoardRepository.deleteAllByIdInBatch(boardNums);
	}

}
