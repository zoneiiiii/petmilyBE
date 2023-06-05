package com.petmily.backend.mypage.Info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class InfoController {
	
	@Autowired
	private final InfoService infoService;
	
	@Autowired
	private final HttpSession httpSession;
	
	@GetMapping("/getInfo")
	MemberInfo getInfo() {
		String id = (String)httpSession.getAttribute("id");
		return infoService.getInfo(id);
	}
	
	@GetMapping("/getNavInfo")
	NavInfo getNavInfo() {
		String id = (String)httpSession.getAttribute("id");
		return infoService.getNavInfo(id);
	}
	
}
