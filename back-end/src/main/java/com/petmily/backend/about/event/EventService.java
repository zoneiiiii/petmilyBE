package com.petmily.backend.about.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petmily.backend.about.domain.EventBoard;
import com.petmily.backend.about.dto.EventForm;
import com.petmily.backend.about.dto.EventList;
import com.petmily.backend.about.dto.EventView;
import com.petmily.backend.global.DataNotFoundException;
import com.petmily.backend.member.login.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {
	private final EventRepository eventRepository;
	private final MemberRepository memberRepository;

	public Page<EventList> getList(int page, int limit, String keyword, String searchMode){
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
		Page<EventList> eventList = null;
		if(event != null) eventList = event.map(e -> EventList.EventToListDto(e));
		return eventList;
	}

	public EventView viewEvent(Long no) {
		Optional<EventView> eventView = this.eventRepository.getEventView(no);
		if(eventView.isPresent()) {
			this.eventRepository.updateViews(no);
			return eventView.get();
		}
		else throw new DataNotFoundException("eventView not found");
	}

	public Boolean saveEvent(EventForm eventForm, String id) {
		System.out.println(id);
		System.out.println(eventForm.getStartDate());
		
		EventBoard event = EventBoard.builder()
				.category("event")
				.member(this.memberRepository.findByMemberId(id))
				.subject(eventForm.getSubject())
				.content(eventForm.getContent())
				.thumbnail(eventForm.getThumbnail())
				.postDate(LocalDateTime.now())
				.startDate(eventForm.getStartDate())
				.endDate(eventForm.getEndDate())
				.count(0)
				.build();
		return this.eventRepository.save(event).getMember().getMemberId().equals(id);
	}
	
	public Boolean checkWriter(Long boardNum, String id) {
		Optional<EventBoard> event = this.eventRepository.findById(boardNum);
		return event.get().getMember().getMemberId().equals(id);
	}
	
	public int updateEvent(EventForm eventForm, String id) {
		return this.eventRepository.updateEvent(eventForm);
		
	}
	
	public long getCount() {
		return this.eventRepository.count();
	}
}
