package com.petmily.backend.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/pet")
public class PetController {
	private final PetService petService;
	private final HttpSession httpSession;

	@Autowired
	public PetController(PetService petService, HttpSession httpSession) {
		this.petService = petService;
		this.httpSession = httpSession;
	}
	
	@PostMapping("/insert")
    public ResponseEntity<PetDto> insertPet(@RequestBody PetDto petDto, HttpSession session){
        System.out.println(session.getAttribute("id"));
        String memberId = (String)session.getAttribute("id");
        PetDto insertPet = petService.insertPet(petDto, memberId);
        return ResponseEntity.ok(insertPet);
    }

}
