package com.petmily.backend.about.event;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.about.dto.EventForm;
import com.petmily.backend.about.dto.EventList;
import com.petmily.backend.about.dto.EventView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/event")
@RequiredArgsConstructor
@RestController
public class EventController {
	
	@Autowired
	private final EventService eventService;
	
	@Autowired
	private final HttpSession httpSession;
	
	@GetMapping("/list")
	public Page<EventList> getList(
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="10") int limit, 
			@RequestParam(value="search", defaultValue="") String search, 
			@RequestParam(value="search_mode", defaultValue="subject") String search_mode) {
		Page<EventList> paging = null;
		try {
			String keyword = URLDecoder.decode(search, "UTF8").replaceAll("[!@#$%^&*()]", " ").trim().replaceAll("\\s+", "|");
			paging = this.eventService.getList(page, limit, keyword, search_mode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paging;
	}
	
	@GetMapping("/view")
	public EventView viewEvent(@RequestParam(value="no")Long no) {
		return this.eventService.viewEvent(no);
	}
	
	@PostMapping("/insert")
	public Boolean insertEvent(@RequestBody@Valid EventForm eventForm, BindingResult bindingResult) {
		String id = (String)httpSession.getAttribute("id");
		if(id != null && !bindingResult.hasErrors()) {
			return this.eventService.saveEvent(eventForm, id);
		}
		else return false;
	}
	
	@GetMapping("/check-writer")
	public Boolean checkWriter(@RequestParam(value="no") Long no) {
		String id = (String)httpSession.getAttribute("id");
		return this.eventService.checkWriter(no, id);
	}
	
	@PostMapping("/update")
	public int updateEvent(@RequestBody@Valid EventForm eventForm, BindingResult bindingResult) {
		String id = (String)httpSession.getAttribute("id");
		if(id != null && !bindingResult.hasErrors()) {
			return this.eventService.updateEvent(eventForm, id);
		}
		else return 0;
	}
	
	@GetMapping("/count")
	public long getCount() {
		return this.eventService.getCount();
	}
}
