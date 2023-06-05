package com.petmily.backend.mypage.Info;

import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InfoService {
	
	private final MemberRepository memberRepository;
	
	MemberInfo getInfo(String id) {
		Member member = memberRepository.findByMemberId(id);
		return MemberInfo.builder()
				.memberNum(member.getMemberNum())
				.memberId(id)
				.memberName(member.getMemberName())
				.memberNickname(member.getMemberNickname())
				.memberGender(member.getMemberGender())
				.memberBirth(member.getMemberBirth())
				.memberTel(member.getMemberTel())
				.memberEmail(member.getMemberEmail())
				.memberImg(member.getMemberImg())
				.build();
	}

	public NavInfo getNavInfo(String id) {
		Member member = memberRepository.findByMemberId(id);
		NavInfo navInfo = NavInfo.builder()
				.memberNum(member.getMemberNum())
				.memberNickname(member.getMemberNickname())
				.memberImg(member.getMemberImg())
				.build();
		return navInfo;
	}
}
