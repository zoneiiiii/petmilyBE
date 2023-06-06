package com.petmily.backend.admin.board.free;

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
public class AdminFreeBoardService {
	private final AdminFreeBoardRepository freeBoardRepository;
	
	public Page<AdminFreeBoard> getAdminFreeBoardList(int page, int limit, String keyword, String searchMode) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<AdminFreeBoard> adminAdoptReviewList = null;
		if(keyword.isEmpty()) {
			adminAdoptReviewList = this.freeBoardRepository.findAllAdminFreeBoard(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				adminAdoptReviewList = this.freeBoardRepository.findByFreeSubjectContainingOrFreeContentContaining(keyword, pageable);
				break;
			case "subject":
				adminAdoptReviewList = this.freeBoardRepository.findByFreeSubjectContaining(keyword, pageable);
				break;
			case "content":
				adminAdoptReviewList = this.freeBoardRepository.findByFreeContentContaining(keyword, pageable);
				break;
			}
		}
		return adminAdoptReviewList;
	}
	
	public void deleteFreeBoardList(List<Long> boardNums) {
		this.freeBoardRepository.deleteAllByIdInBatch(boardNums);
	}

}
