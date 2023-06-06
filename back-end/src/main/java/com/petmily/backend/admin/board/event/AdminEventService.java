package com.petmily.backend.admin.board.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.about.domain.EventBoard;
import com.petmily.backend.about.event.EventRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminEventService {
	private final EventRepository eventRepository;

	public Page<AdminEventBoard> getList(int page, int limit, String keyword, String searchMode){
		System.out.println("service: " + keyword);
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("postDate"));
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorts));
		
		Page<EventBoard> event = null;
		
		if(keyword.isEmpty()) {
			event = this.eventRepository.findAll(pageable);
		}
		else {
			switch(searchMode) {
			case "subject_content":
				event = this.eventRepository.findBySubjectContainingOrContentContaining(keyword, pageable);
				break;
			case "subject":
				event = this.eventRepository.findBySubjectContaining(keyword, pageable);
				break;
			case "content":
				event = this.eventRepository.findByContentContaining(keyword, pageable);
				break;
			}
		}
		Page<AdminEventBoard> eventList = null;
		if(event != null) eventList = event.map(e -> AdminEventBoard.EventBoardToAdminEventBoard(e));
		return eventList;
	}
	
	public void deleteEventBoardList(List<Long> boardNums) {
		this.eventRepository.deleteAllByIdInBatch(boardNums);
	}
}
