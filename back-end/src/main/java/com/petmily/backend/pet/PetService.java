package com.petmily.backend.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.repository.MemberRepository;
import com.petmily.backend.member.login.service.MemberService;

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
        pet.setPetAge(petDto.getPetAge());
        pet.setPetImg(petDto.getPetImg());
        pet.setPetSpecies(petDto.getPetSpecies());
        pet.setShelterName(petDto.getShelterName());
        pet.setShelterTel(petDto.getShelterTel());
        pet.setShelterAddr(petDto.getShelterAddr());
        pet.setShelterDate(petDto.getShelterDate());

        repository.save(pet);

        return convertToDto(pet);
    }
	private PetDto convertToDto(Pet pet){
		 
		PetDto petDto = new PetDto();
		petDto.setMemberNum(pet.getMemberNum());
		petDto.setPetName(pet.getPetName());
		petDto.setPetAge(pet.getPetAge());
		petDto.setPetImg(pet.getPetImg());
		petDto.setPetSpecies(pet.getPetSpecies());
		petDto.setShelterName(pet.getShelterName());
		petDto.setShelterTel(pet.getShelterTel());
		petDto.setShelterAddr(pet.getShelterAddr());
		petDto.setShelterDate(pet.getShelterDate());
 
        return petDto;
     }

}
