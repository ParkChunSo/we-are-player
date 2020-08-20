package com.wap.api.profile.club.controller;

import com.wap.api.domain.enums.ClubMemberType;
import com.wap.api.profile.club.dtos.params.ClubInfoParam;
import com.wap.api.profile.club.dtos.params.LocationInfoParam;
import com.wap.api.profile.club.dtos.request.ClubInfoUpdateDto;
import com.wap.api.profile.club.dtos.request.ClubLeaderUpdateDto;
import com.wap.api.profile.club.dtos.request.ClubMemberSaveDto;
import com.wap.api.profile.club.dtos.response.ClubInfoDto;
import com.wap.api.profile.club.dtos.response.ClubMemberDto;
import com.wap.api.profile.club.service.ClubMemberService;
import com.wap.api.profile.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/club")
public class ClubController {
    private final ClubService clubService;
    private final ClubMemberService clubMemberService;

    @PostMapping
    public void createClub(@RequestParam("image") MultipartFile image, @RequestBody ClubInfoDto dto) throws IOException {
        clubService.createClub(image, dto);
    }

    @GetMapping(value = "/info")
    public ClubInfoDto findByClubInfo(@ModelAttribute ClubInfoParam dto){
        return clubService.getClubInfo(dto);
    }

    @GetMapping(value = "/info/{name}")
    public List<ClubInfoDto> findByClubName(@PathVariable String name){
        return clubService.findByClubName(name);
    }

    @GetMapping(value = "/info/location")
    public List<ClubInfoDto> findByLocation(@ModelAttribute LocationInfoParam dto){
        return clubService.findByLocation(dto);
    }

    @GetMapping(value = "/info/members")
    public List<ClubMemberDto> findMembersByClubInfo(@ModelAttribute ClubInfoParam dto){
        return clubMemberService.getClubMembers(dto, ClubMemberType.MEMBER);
    }

    @GetMapping(value = "/info/mercenaries")
    public List<ClubMemberDto> findMercenariesByClubInfo(@ModelAttribute ClubInfoParam dto){
        return clubMemberService.getClubMembers(dto, ClubMemberType.MERCENARY);
    }

    @PostMapping(value = "/member")
    public void saveClubMembers(@RequestHeader(name = "Authorization") String token, @RequestBody ClubMemberSaveDto dto){
        clubMemberService.saveClubMember(token, dto);
    }

    @PutMapping
    public void updateClub(@RequestBody ClubInfoUpdateDto dto){
        clubService.updateLikeAndRudeCnt(dto);
    }

    @PutMapping(value = "/image")
    public void updateImage(@RequestParam("image")MultipartFile image, @ModelAttribute ClubInfoParam dto) throws IOException {
        clubService.updateClubLogoUri(image, dto);
    }

    @PutMapping(value = "/leader")
    public void updateLeader(@RequestBody ClubLeaderUpdateDto dto){
        clubMemberService.updateClubLeader(dto);
    }

    @DeleteMapping
    public void deleteClub(@ModelAttribute ClubInfoParam dto){
        clubService.deleteClub(dto);
    }

}

