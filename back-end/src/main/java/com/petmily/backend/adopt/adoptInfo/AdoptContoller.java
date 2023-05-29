package com.petmily.backend.adopt.adoptInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("/adopt")
public class AdoptContoller {
	private final AdoptService adoptService;
	private final HttpSession httpSession;

	@Autowired
	public AdoptContoller(AdoptService adoptService, HttpSession httpSession) {
		this.adoptService = adoptService;
		this.httpSession = httpSession;
	}
	
	@PostMapping("/insert")
    public ResponseEntity<AdoptDto> application(@RequestBody AdoptDto adoptDto, HttpSession session){
        System.out.println(session.getAttribute("id"));
        String memberId = (String)session.getAttribute("id");
        AdoptDto application = adoptService.application(adoptDto, memberId);
        return ResponseEntity.ok(application);
    }

}
