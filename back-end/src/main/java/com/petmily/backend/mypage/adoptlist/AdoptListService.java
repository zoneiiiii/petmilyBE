package com.petmily.backend.mypage.adoptlist;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.petmily.backend.member.login.repository.MemberRepository;
import com.petmily.backend.pet.Pet;
import com.petmily.backend.pet.PetDto;
import com.petmily.backend.pet.PetRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdoptListService {
	
	private final PetRepository petRepository;
	private final MemberRepository memberRepository;
	
	public List<PetDto> getList(String memberId) {
		Long memberNum = memberRepository.findByMemberId(memberId).getMemberNum();
		if(memberNum != null) {
		List<Pet> petList = petRepository.findAllByMemberNum(memberNum);	
		List<PetDto> petDtoList = petList.stream().map(pet -> convertToDto(pet)).toList();
		return petDtoList;
		}
		else return null;
	}
	
	public PetDto updateName(AdoptListRequest request) {
		System.out.println("service: request.petNum: " + request.getPetNum() + ", petName: " + request.getPetName());
		Pet pet = petRepository.findById(request.getPetNum()).get();
		
		pet.setPetName(request.getPetName());
		System.out.println("service: pet.petName: " + pet.getPetName());
		PetDto petDto = convertToDto(petRepository.save(pet));
		System.out.println("service: saved pet.petName: " + pet.getPetName());
		return petDto;
	}
	
	public PetDto updateImg(AdoptListRequest request) {
		Pet pet = petRepository.findById(request.getPetNum()).get();
		pet.setPetImg(request.getPetImg());
		PetDto petDto = convertToDto(petRepository.save(pet));
		return petDto;
	}

	private PetDto convertToDto(Pet pet){
		 
		PetDto petDto = new PetDto();
		petDto.setPetNum(pet.getPetNum());
		petDto.setMemberNum(pet.getMemberNum());
		petDto.setPetName(pet.getPetName());
		petDto.setPetAge(pet.getPetAge());
		petDto.setPetImg(pet.getPetImg());
		petDto.setPetSpecies(pet.getPetSpecies());
		petDto.setShelterName(pet.getShelterName());
		petDto.setShelterTel(pet.getShelterTel());
		petDto.setShelterAddr(pet.getShelterAddr());
		petDto.setShelterDate(pet.getShelterDate());
		petDto.setSexCd(pet.getSexCd());
		petDto.setNeuterYn(pet.getNeuterYn());
 
        return petDto;
     }
}
