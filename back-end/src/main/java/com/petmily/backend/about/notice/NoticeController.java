package com.petmily.backend.about.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.about.dto.NoticeForm;
import com.petmily.backend.about.dto.NoticeList;
import com.petmily.backend.about.dto.NoticeView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@RestController
public class NoticeController {
	
	@Autowired
	private final NoticeService noticeService;
	
	@Autowired
	private final HttpSession httpSession;
	
	@GetMapping("/list")
	public Page<NoticeList> getList(
			@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="20") int limit, 
			@RequestParam(value="search", defaultValue="") String search, 
			@RequestParam(value="search_mode", defaultValue="subject") String search_mode) {
		Page<NoticeList> paging = this.noticeService.getList(page, limit, search, search_mode);
		return paging;
	}
	
	@GetMapping("/view")
	public NoticeView viewNotice(@RequestParam(value="no")Long no) {
		return this.noticeService.viewNotice(no);
	}
	
	@PostMapping("/insert")
	public String insertNotice(@Valid NoticeForm noticeForm, BindingResult bindingResult) {
		String id = (String)httpSession.getAttribute("id");
		if(!bindingResult.hasErrors()) {
			this.noticeService.insertNotice(noticeForm, id);
		}
		
		return id;
	}
//	@PostMapping("/delete")
//	public void deleteNotice(Long boardNum) {
//		return this.noticeService.deleteNotice(boardNum)
//	}
//	@GetMapping("/deleteAll")
//	public void deleteAll() {
//		return this.noticeService.deleteAll();
//	}
}
