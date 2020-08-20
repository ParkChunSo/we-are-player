package com.wap.api.profile.member.controller;

import com.wap.api.profile.member.dtos.*;
import com.wap.api.profile.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/member")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public void signUpClient(@RequestParam("image") MultipartFile image, @RequestBody MemberSignUpDto dto) throws IOException {
        memberService.signUp(dto, image);
    }

    @PostMapping("/admin")
    public void signUpAdmin(@RequestHeader(name = "Authorization") String token
            , @RequestParam("image") MultipartFile image, @RequestBody MemberSignUpDto dto) throws IOException {
        memberService.signUp(token, dto, image);
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

    @PutMapping("/image")
    public void updateImage(@RequestParam("image") MultipartFile image, @RequestParam("id") String id) throws IOException {
        memberService.updateImage(id, image);
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
