package com.chun.apps.member.controller;

import com.chun.apps.member.dtos.*;
import com.chun.apps.member.dtos.request.MemberLoginReqDto;
import com.chun.apps.member.dtos.request.MemberSaveReqDto;
import com.chun.apps.member.service.MemberService;
import com.chun.commons.dtos.member.MemberResDto;
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
    public void signUpClient(@RequestParam(value = "image", required = false) MultipartFile image, @RequestBody MemberSaveReqDto dto) throws IOException {
        memberService.signUp(dto, image);
    }

    @PostMapping("/admin")
    public void signUpAdmin(@RequestHeader(name = "Authorization") String token
            , @RequestParam(value = "image", required = false) MultipartFile image, @RequestBody MemberSaveReqDto dto) throws IOException {
        memberService.signUp(token, dto, image);
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberLoginReqDto dto) {
        return memberService.login(dto);
    }

    @GetMapping("/{userId}")
    public MemberResDto findById(@PathVariable String userId) {
        return memberService.find(userId);
    }

//    @GetMapping("/details/{userId}")
//    public MemberDetailsInfoDto findDetailsInfoById(@RequestHeader(name = "Authorization") String token, @PathVariable String userId) {
//        return memberService.getMemberDetailsInfo(userId, token);
//    }

    @GetMapping("/all")
    public List<MemberResDto> findAllMember() {
        return memberService.findAll();
    }

    @PutMapping
    public void updateMemberInfo(@RequestHeader(name = "Authorization") String token, @RequestBody MemberUpdateReqDto dto) {
        memberService.update(token, dto);
    }

    @PutMapping("/image")
    public void updateImage(@RequestHeader(name = "Authorization") String token, @RequestParam("image") MultipartFile image, @RequestParam("id") String id) throws IOException {
        memberService.updateImage(token, id, image);
    }

    @PutMapping("/password")
    public void updatePassword(@RequestHeader(name = "Authorization") String token, @RequestBody MemberPasswordUpdateReqDto dto) {
        memberService.updatePassword(token, dto);
    }

    @DeleteMapping("/{userId}")
    public void deleteMember(@RequestHeader(name = "Authorization") String token, @PathVariable String id) {
        memberService.deleteMember(token, id);
    }
}
