package com.wap.chun.profile.member.controller;

import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.error.exception.ClubAlreadyExistException;
import com.wap.chun.profile.member.dtos.*;
import com.wap.chun.profile.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUp")
    public void signUp(@RequestHeader(name = "Authorization") String token, @RequestBody MemberSignUpDto dto){
        memberService.signUp(token, dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberLoginDto dto){
        return memberService.login(dto);
    }

    @GetMapping("/{userId}")
    public MemberInfoDto getMemberInfo(@PathVariable String userId){
        return memberService.getMemberInfo(userId);
    }

    @GetMapping("/details/{userId}")
    public MemberDetailsInfoDto getMemberDetailsInfo(@RequestHeader(name = "Authorization") String token, @PathVariable String userId){
        return memberService.getMemberDetailsInfo(userId, token);
    }

    @PutMapping("/update")
    public void updateMemberInfo(@RequestBody MemberInfoUpdateDto dto){
        memberService.updateMemberInfo(dto);
    }

    @PutMapping("/update/password")
    public void updatePassword(@RequestBody MemberPasswordUpdateDto dto){
        memberService.updateMemberPassword(dto);
    }

    @DeleteMapping("/delete")
    public void deleteMember(@RequestBody MemberDeleteDto dto){
        memberService.deleteMember(dto);
    }

    @GetMapping("/all")
    public List<MemberInfoDto> getAllMember(){
        return memberService.getAllMember();
    }
}
