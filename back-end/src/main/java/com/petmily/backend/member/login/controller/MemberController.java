package com.petmily.backend.member.login.controller;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.dto.MemberDto;
import com.petmily.backend.member.login.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MemberController {
    private final MemberService memberService;
    private final HttpSession httpSession;

    public MemberController(MemberService memberService, HttpSession httpSession){
        this.memberService = memberService;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public long login(@RequestBody MemberDto dto){
        long loginResult = memberService.login(dto);
        if(loginResult == 1) { //로그인 성공시
            httpSession.setAttribute("id", dto.getMemberId()); //httpSession에 session 추가 (서버측 관리)
            log.info("User {} 로그인 성공 ", dto.getMemberId());
            log.info("세션ID값 : {}" , (String) httpSession.getAttribute("id"));
        }else{
            log.warn("User {} 로그인 실패",dto.getMemberId());
        }
        return loginResult;
    }

    @PostMapping("/selectMember")
    public Member selectMember(@RequestBody MemberDto dto){
        return memberService.selectMember(dto);
    }

    @PostMapping("/findpw")
    public long findmember(@RequestBody MemberDto dto){
        return memberService.findMember(dto);
    }

    @PutMapping("/changepw")
    public int pwChange(@RequestBody MemberDto dto){
        return memberService.pwChange(dto);
    }

}
