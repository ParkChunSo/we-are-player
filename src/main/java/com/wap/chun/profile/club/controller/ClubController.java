package com.wap.chun.profile.club.controller;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.profile.club.dtos.*;
import com.wap.chun.profile.club.service.ClubMemberService;
import com.wap.chun.profile.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/club")
public class ClubController {
    private final ClubService clubService;
    private final ClubMemberService clubMemberService;

    @PostMapping(value = "/create")
    public void createClub(@RequestBody ClubInfoDto dto){
        clubService.createClub(dto);
    }

    @GetMapping(value = "/info/name/{clubName}/city/{city}/district/{district}")
    public ClubInfoDto getClubInfo(@PathVariable("clubName") String clubName, @PathVariable("city") String city, @PathVariable("district") String district){
        return clubService.getClubInfo(clubName, city, district);
    }

    @GetMapping(value = "/info/name/{clubName}")
    public List<ClubInfoDto> findByClubName(@PathVariable String clubName){
        return clubService.findByClubName(clubName);
    }

    @GetMapping(value = "/info/city/{city}/district/{district}")
    public List<ClubInfoDto> findByLocation(@PathVariable String city, @PathVariable String district){
        return clubService.findByLocation(city, district);
    }

    @GetMapping(value = "/info/name/{clubName}/city/{city}/district/{district}/members")
    public List<ClubMemberDto> getClubMembers(@PathVariable String clubName, @PathVariable String city, @PathVariable String district){
        return clubMemberService.getClubMembers(clubName, city, district, ClubMemberType.MEMBER);
    }

    @GetMapping(value = "/info/name/{clubName}/city/{city}/district/{district}/mercenaries")
    public List<ClubMemberDto> getClubMercenaries(@PathVariable String clubName, @PathVariable String city, @PathVariable String district){
        return clubMemberService.getClubMembers(clubName, city, district, ClubMemberType.MERCENARY);
    }

    @PostMapping(value = "/member/save")
    public void saveClubMembers(@RequestHeader(name = "Authorization") String token, @RequestBody ClubMemberSaveDto dto){
        clubMemberService.saveClubMember(token, dto);
    }

    @PutMapping(value = "/update/leader")
    public void updateLeader(@RequestBody ClubLeaderUpdateDto dto){
        clubMemberService.updateClubLeader(dto);
    }

    @PutMapping (value = "/update")
    public void updateClub(@RequestBody ClubInfoUpdateDto dto){
        clubService.updateClubLogoUri(dto);
        clubService.updateLikeAndRudeCnt(dto);
    }

    @DeleteMapping(value = "/delete/name/{clubName}/city/{city}/district/{district}")
    public void deleteClub(@PathVariable String clubName, @PathVariable String city, @PathVariable String district){
        clubService.deleteClub(clubName, city, district);
    }

}

