package com.petmily.backend.admin.board.notice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.about.domain.Notice;
import com.petmily.backend.about.notice.NoticeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminNoticeService {
	private final NoticeRepository noticeRepository;

	public Page<AdminNotice> getList(int page, int limit, String keyword, String searchMode){
		System.out.println("service: " + keyword);
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<Notice> notice = null;
		
		if(keyword.isEmpty()) {
			notice = this.noticeRepository.findAll(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				notice = this.noticeRepository.findBySubjectContainingOrContentContaining(keyword, pageable);
				break;
			case "subject":
				notice = this.noticeRepository.findBySubjectContaining(keyword, pageable);
				break;
			case "content":
				notice = this.noticeRepository.findByContentContaining(keyword, pageable);
				break;
			}
		}
		Page<AdminNotice> noticeList = notice.map(n -> AdminNotice.NoticeToAdminNotice(n));
		return noticeList;
	}
	
	public void deleteNoticeList(List<Long> boardNums) {
		this.noticeRepository.deleteAllByIdInBatch(boardNums);
	}
}
