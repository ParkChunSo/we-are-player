package com.wap.chun.profile.member.controller;

import com.wap.chun.domain.enums.MemberRole;
import com.wap.chun.profile.member.dtos.*;
import com.wap.chun.profile.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/client/signUp")
    public void signUpForClient(@RequestBody MemberSignUpDto dto){
        memberService.signUp(dto, MemberRole.CLIENT);
    }

    @PostMapping("/admin/signUp")
    public void signUpForAdmin(@RequestBody MemberSignUpDto dto){
        memberService.signUp(dto, MemberRole.ADMIN);
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

    @PostMapping("/update")
    public void updateMemberInfo(@RequestBody MemberInfoUpdateDto dto){
        memberService.updateMemberInfo(dto);
    }

    @PostMapping("/update/password")
    public void updatePassword(@RequestBody MemberPasswordUpdateDto dto){
        memberService.updateMemberPassword(dto);
    }

    @PostMapping("/delete")
    public void deleteMember(@RequestBody MemberDeleteDto dto){
        memberService.deleteMember(dto);
    }
}
