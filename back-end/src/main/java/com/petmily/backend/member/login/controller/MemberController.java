package com.petmily.backend.member.login.controller;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.dto.MemberDto;
import com.petmily.backend.member.login.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public long login(@RequestBody MemberDto dto){
        return memberService.login(dto);
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
