package com.petmily.backend.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmily.backend.adopt.adoptInfo.Adopt;
import com.petmily.backend.adopt.adoptReview.ReviewBoard;
import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.repository.MemberRepository;
import com.petmily.backend.member.login.service.MemberService;

import jakarta.transaction.Transactional;

@Service
public class PetService {
	@Autowired
	private final PetRepository repository;
	private final MemberRepository memberRepository;
	private final MemberService memberService;

	public PetService(PetRepository repository, MemberRepository memberRepository, MemberService memberService){
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
     
    }
	
	public PetDto insertPet(PetDto petDto, String memberId){
        Member member = memberService.getMember(memberId);
        Pet pet = new Pet();

        pet.setMemberNum(member.getMemberNum());
        pet.setPetName(petDto.getPetName());
 
        
        repository.save(pet);

        return convertToDto(pet);
    }
	
	@Transactional 
    public Pet getPetDetail(String petName) {
    	Pet getPetDetail = repository.findByPetName(petName);
        return getPetDetail;
    }
	 @Transactional
	 public void deleteAllByPetName(String petName){
		 repository.deleteAllByPetName(petName);
	 }
	 
	 @Transactional
	 public List<Pet> petList(){
		 return repository.findAll();
	 }
	private PetDto convertToDto(Pet pet){
		 
		PetDto petDto = new PetDto();
		petDto.setMemberNum(pet.getMemberNum());
		petDto.setPetName(pet.getPetName());
//		petDto.setPetAge(pet.getPetAge());
//		petDto.setPetImg(pet.getPetImg());
//		petDto.setPetSpecies(pet.getPetSpecies());
//		petDto.setShelterName(pet.getShelterName());
//		petDto.setShelterTel(pet.getShelterTel());
//		petDto.setShelterAddr(pet.getShelterAddr());
//		petDto.setApplicateDate(pet.getApplicateDate());
//
//		petDto.setSexCd(pet.getSexCd());
//		petDto.setNeuterYn(pet.getNeuterYn());
 
        return petDto;
     }

}
