package com.wap.api.profile.member.controller;

import com.wap.api.profile.member.dtos.*;
import com.wap.api.profile.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public void signUpClient(@RequestBody MemberSignUpDto dto) {
        memberService.signUp(dto);
    }

    @PostMapping("/admin")
    public void signUpAdmin(@RequestHeader(name = "Authorization") String token, @RequestBody MemberSignUpDto dto) {
        memberService.signUp(token, dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberLoginDto dto) {
        return memberService.login(dto);
    }

    @GetMapping("/{userId}")
    public MemberInfoDto findById(@PathVariable String userId) {
        return memberService.getMemberInfo(userId);
    }

    @GetMapping("/details/{userId}")
    public MemberDetailsInfoDto findDetailsInfoById(@RequestHeader(name = "Authorization") String token, @PathVariable String userId) {
        return memberService.getMemberDetailsInfo(userId, token);
    }

    @GetMapping("/all")
    public List<MemberInfoDto> findAllMember() {
        return memberService.getAllMember();
    }

    @PutMapping
    public void updateMemberInfo(@RequestBody MemberInfoUpdateDto dto) {
        memberService.updateMemberInfo(dto);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestBody MemberPasswordUpdateDto dto) {
        memberService.updateMemberPassword(dto);
    }

    @DeleteMapping
    public void deleteMember(@RequestBody MemberDeleteDto dto) {
        memberService.deleteMember(dto);
    }
}
