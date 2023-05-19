package com.petmily.backend.member.login.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.dto.MemberDto;
import com.petmily.backend.member.login.dto.MemberUpdateRequest;
import com.petmily.backend.member.login.repository.MemberRepository;

import jakarta.transaction.Transactional;

import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder; //PW DB 암호화를 위한 Security 제공 PWEncoder 추가

    public MemberService(MemberRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public long login(MemberDto dto){
        Member member = repository.findByMemberId(dto.getMemberId());
        if (member != null && passwordEncoder.matches(dto.getMemberPw(), member.getMemberPw())){
            return 1;
        }else {
            return 0;
        }
//        return repository.countByMemberIdAndMemberPw(dto.getMemberId(), dto.getMemberPw());
    }

    public Member selectMember(MemberDto dto){
        return repository.findByMemberId(dto.getMemberId());
    }

    public long findMember(MemberDto dto){
        return repository.countByMemberIdAndMemberEmail(dto.getMemberId(),dto.getMemberEmail());
    }

    public int pwChange(MemberDto dto){
        Member member = repository.findByMemberId(dto.getMemberId());
        if (member != null){
            member.setMemberPw(passwordEncoder.encode(dto.getMemberPw()));
            repository.save(member);
            return 1;
        }else {
            return 0;
        }
    }
    
<<<<<<< HEAD
    public Member getMember(String memberId){
        Member member = repository.findByMemberId(memberId);
         if(member == null){
             throw new NoSuchElementException("해당 멤버ID 에 맞는 정보를 찾을 수 없습니다. : " + memberId);
         }

         return member;
     }
=======
    //@Transactional
    public boolean updateMember(Long memberNum, MemberUpdateRequest request) {
        Optional<Member> optionalMember = repository.findById(memberNum);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setMemberNickname(request.getMemberNickname());
            member.setMemberPw(passwordEncoder.encode(request.getMemberPw()));
            member.setMemberEmail(request.getMemberEmail());
            member.setMemberTel(request.getMemberTel());
            member.setMemberImg(request.getMemberImg());
            repository.save(member);
            return true;
        }
        return false;
    }
>>>>>>> 472094534c347628ecb765fb876e6d6edb279bfb
}
