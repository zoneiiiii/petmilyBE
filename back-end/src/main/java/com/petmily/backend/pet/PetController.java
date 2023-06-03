package com.petmily.backend.pet;

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

import com.petmily.backend.adopt.adoptInfo.Adopt;
import com.petmily.backend.adopt.adoptReview.ReviewBoard;

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
	
	@GetMapping("/{petName}")
    public Pet getBoard(@PathVariable String petName) {
        return petService.getPetDetail(petName);
    }
	@GetMapping("/list")
	public List<Pet> petList(Model model){
//		String memberId = (String)session.getAttribute("id");
		List<Pet> petList = petService.petList();
	    model.addAttribute("list",petList);
	    return petList;
	}
	
	@DeleteMapping("/{petName}")
	public void deleteAllByPetName(@PathVariable String petName){
		petService.deleteAllByPetName(petName);
	}

	
	

}
