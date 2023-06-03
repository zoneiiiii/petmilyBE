package com.petmily.backend.mypage.adoptlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.pet.PetDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/mypage/adoptList")
@RequiredArgsConstructor
@RestController
public class AdoptListController {
	
	@Autowired
	private final AdoptListService adoptListService;
	
	@Autowired
	private final HttpSession httpSession;
	
	@GetMapping("/getList")
	public List<PetDto> getList() {
		String id = (String)httpSession.getAttribute("id");
		if(id == null) return null;
		return adoptListService.getList(id);
	}
	
	@PostMapping("/update/name")
	public PetDto updateName(@RequestBody @Valid AdoptListRequest request, BindingResult bindingResult) {
		System.out.println("controller: request.petNum: " + request.getPetNum() + ", petName: " + request.getPetName() + ", petImg: " + request.getPetImg());
		if(!bindingResult.hasErrors()) {
			
		return adoptListService.updateName(request);
		}
		else return null;
	}
	
	@PostMapping("/update/img")
	public PetDto updateImg(@RequestBody @Valid AdoptListRequest request) {
		return adoptListService.updateImg(request);
		
	}
	
}
