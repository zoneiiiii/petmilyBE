package com.petmily.backend.mypage.adoptlist;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petmily.backend.adopt.adoptInfo.Adopt;
import com.petmily.backend.member.login.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdoptListService {
	
	private final AdoptListRepository adoptRepository;
	private final MemberRepository memberRepository;
	
	public List<AdoptListResponse> getList(String memberId) {
		Long memberNum = memberRepository.findByMemberId(memberId).getMemberNum();
		if(memberNum != null) {
		List<Adopt> adoptList = adoptRepository.findAllByMemberNumAndAdoptStateNotOrderByAdoptDateAsc(memberNum, "fail");	
		List<AdoptListResponse> AdoptListResponseList = adoptList.stream().map(adopt -> AdoptListResponse.AdoptToAdoptList(adopt)).toList();
		return AdoptListResponseList;
		}
		else return null;
	}
	
	public AdoptListResponse updateName(AdoptListRequest request) {
		System.out.println("service: request.adoptNum: " + request.getAdoptNum() + ", petName: " + request.getPetName());
		Adopt adopt = adoptRepository.findById(request.getAdoptNum()).get();
		
		adopt.setPetName(request.getPetName());
		System.out.println("service: pet.petName: " + adopt.getPetName());
		AdoptListResponse adoptListResponse = AdoptListResponse.AdoptToAdoptList(adoptRepository.save(adopt));
		System.out.println("service: saved pet.petName: " + adopt.getPetName());
		return adoptListResponse;
	}
	
	public AdoptListResponse updateImg(AdoptListRequest request) {
		Adopt adopt = adoptRepository.findById(request.getAdoptNum()).get();
		adopt.setPetImg(request.getPetImg());
		AdoptListResponse adoptListResponse = AdoptListResponse.AdoptToAdoptList(adoptRepository.save(adopt));
		return adoptListResponse;
	}

}
