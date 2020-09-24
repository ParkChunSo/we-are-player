package com.chun.apps.club.controller;

import com.chun.apps.club.dtos.params.ClubInfoParam;
import com.chun.apps.club.dtos.params.LocationInfoParam;
import com.chun.apps.club.dtos.request.ClubInfoUpdateReqDto;
import com.chun.apps.club.dtos.request.ClubLeaderUpdateReqDto;
import com.chun.apps.club.dtos.request.ClubMemberSaveReqDto;
import com.chun.apps.club.dtos.request.ClubSaveReqDto;
import com.chun.commons.dtos.club.ClubMemberResDto;
import com.chun.commons.dtos.club.ClubResDto;
import com.chun.apps.club.service.ClubMemberService;
import com.chun.commons.enums.ClubMemberType;
import com.chun.apps.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final ClubMemberService clubMemberService;

    @PostMapping
    public void createClub(@RequestParam(value = "image", required = false) Optional<MultipartFile> image, @RequestBody ClubSaveReqDto dto) throws IOException {

        clubService.save(image, dto);
    }

    @GetMapping(value = "/info")
    public ClubResDto findByClubInfo(@ModelAttribute ClubInfoParam dto){
        return clubService.find(dto);
    }

    @GetMapping(value = "/info/{name}")
    public List<ClubResDto> findByClubName(@PathVariable String name){
        return clubService.findByClubName(name);
    }

    @GetMapping(value = "/info/location")
    public List<ClubResDto> findByLocation(@ModelAttribute LocationInfoParam dto){
        return clubService.findByLocation(dto);
    }

    @GetMapping(value = "/info/members")
    public List<ClubMemberResDto> findMembersByClubInfo(@ModelAttribute ClubInfoParam dto){
        return clubMemberService.findByClub(dto, ClubMemberType.MEMBER);
    }

    @GetMapping(value = "/info/mercenaries")
    public List<ClubMemberResDto> findMercenariesByClubInfo(@ModelAttribute ClubInfoParam dto){
        return clubMemberService.findByClub(dto, ClubMemberType.MERCENARY);
    }

    @PostMapping(value = "/member")
    public void saveClubMembers(@RequestHeader(name = "Authorization") String token, @RequestBody ClubMemberSaveReqDto dto){
        clubMemberService.saveClubMember(token, dto);
    }

    @PutMapping
    public void updateClub(@RequestBody ClubInfoUpdateReqDto dto){
        clubService.updateLikeAndRudeCnt(dto);
    }

    @PutMapping(value = "/image")
    public void updateImage(@RequestParam("image")MultipartFile image, @ModelAttribute ClubInfoParam dto) throws IOException {
        clubService.updateLogo(image, dto);
    }

    @PutMapping(value = "/leader")
    public void updateLeader(@RequestBody ClubLeaderUpdateReqDto dto){
        clubMemberService.updateClubLeader(dto);
    }

    @DeleteMapping
    public void deleteClub(@ModelAttribute ClubInfoParam dto){
        clubService.delete(dto);
    }
}
