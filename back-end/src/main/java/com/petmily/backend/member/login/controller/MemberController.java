package com.petmily.backend.member.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.petmily.backend.member.login.domain.Member;
import com.petmily.backend.member.login.dto.MemberDto;
import com.petmily.backend.member.login.dto.MemberUpdateRequest;
import com.petmily.backend.member.login.service.MemberService;

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
    
    @PutMapping("/update/{memberNum}")
    public ResponseEntity<String> updateMember(@PathVariable Long memberNum, @RequestBody MemberUpdateRequest request) {
        boolean updated = memberService.updateMember(memberNum, request);
        if (updated) {
            return ResponseEntity.ok("Member updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
