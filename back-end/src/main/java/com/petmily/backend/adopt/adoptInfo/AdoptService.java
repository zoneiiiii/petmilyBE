package com.petmily.backend.adopt.adoptInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        adopt.setPetAge(adoptDto.getPetAge());
        adopt.setPetSpecies(adoptDto.getPetSpecies());
        adopt.setShelterName(adoptDto.getShelterName());
        adopt.setShelterTel(adoptDto.getShelterTel());
        adopt.setShelterAddr(adoptDto.getShelterAddr());
        adopt.setSexCd(adoptDto.getSexCd());
        adopt.setNeuterYn(adoptDto.getNeuterYn());

        repository.save(adopt);

        return convertToDto(adopt);
    }
 
	@Transactional
    public Page<Adopt> adoptList(Pageable pageable){
    	
        return repository.findAllByOrderByAdoptDateDesc(pageable);
    }
	
	@Transactional
	public AdoptCountDto getAdoptCounts() {
		AdoptCountDto counts = new AdoptCountDto();
		counts.setTotalCount(repository.count());
		counts.setWaitingCount(repository.countByAdoptState("wait"));
		counts.setSuccessCount(repository.countByAdoptState("success"));
		counts.setFailCount(repository.countByAdoptState("fail"));
		return counts;
	}
	
	@Transactional
	public void updateAdopt(Long adoptNum, Adopt adopt) {
		Adopt findAdopt = repository.findByAdoptNum(adoptNum);
		findAdopt.setAdoptState(adopt.getAdoptState());
		findAdopt.setApprovedDate(adopt.getApprovedDate());
		
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
		adoptDto.setPetAge(adopt.getPetAge());
		adoptDto.setPetSpecies(adopt.getPetSpecies());
		adoptDto.setShelterName(adopt.getShelterName());
		adoptDto.setShelterTel(adopt.getShelterTel());
		adoptDto.setShelterAddr(adopt.getShelterAddr());
		adoptDto.setSexCd(adopt.getSexCd());
		adoptDto.setNeuterYn(adopt.getNeuterYn());
 
         return adoptDto;
     }

}
