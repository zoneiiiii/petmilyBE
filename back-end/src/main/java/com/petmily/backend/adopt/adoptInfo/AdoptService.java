package com.petmily.backend.adopt.adoptInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.repository.MemberRepository;
import com.petmily.backend.member.login.service.MemberService;

import jakarta.transaction.Transactional;


@Service
public class AdoptService {
	@Autowired
	private final AdoptRepository repository;
	private final MemberRepository memberRepository;
	private final MemberService memberService;

	public AdoptService(AdoptRepository repository, MemberRepository memberRepository, MemberService memberService){
        this.repository = repository;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
     
    }

	public AdoptDto application(AdoptDto adoptDto, String memberId){
        Member member = memberService.getMember(memberId);
        Adopt adopt = new Adopt();

        adopt.setMemberNum(member.getMemberNum());
        adopt.setPetName(adoptDto.getPetName());
        adopt.setPetImg(adoptDto.getPetImg());
        adopt.setAdoptDate(adoptDto.getAdoptDate());
        adopt.setAdopterAddr(adoptDto.getAdopterAddr());
        adopt.setAdopterTel(adoptDto.getAdopterTel());
        adopt.setAdopterBirth(adoptDto.getAdopterBirth());
        adopt.setAdopterEmail(adoptDto.getAdopterEmail());
        adopt.setAdopterName(adoptDto.getAdopterName());
        adopt.setAdoptState(adoptDto.getAdoptState());

        repository.save(adopt);

        return convertToDto(adopt);
    }
 

	
    
	private AdoptDto convertToDto(Adopt adopt){
		 
		AdoptDto adoptDto = new AdoptDto();
		adoptDto.setMemberNum(adopt.getMemberNum());
		adoptDto.setPetName(adopt.getPetName());
		adoptDto.setPetImg(adopt.getPetImg());
		adoptDto.setAdoptDate(adopt.getAdoptDate());
		adoptDto.setAdopterAddr(adopt.getAdopterAddr());
		adoptDto.setAdopterTel(adopt.getAdopterTel());
		adoptDto.setAdopterBirth(adopt.getAdopterBirth());
		adoptDto.setAdopterEmail(adopt.getAdopterEmail());
		adoptDto.setAdopterName(adopt.getAdopterName());
		adoptDto.setAdoptState(adopt.getAdoptState());
 
         return adoptDto;
     }

}
