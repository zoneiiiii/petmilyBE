package com.petmily.backend.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.member.login.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {
	@Autowired
	private final MemberService memberService;
	@Autowired
	private final HttpSession httpSession;
	
	@GetMapping("/check-admin")
	public Boolean checkAdmin() {
		try {
			String id = (String)httpSession.getAttribute("id");
			Long memberNum = memberService.getMember(id).getMemberNum();
			String role = memberService.getMemberRoll(memberNum);
			if(role != null) return "Admin".equals(role);
			else return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
