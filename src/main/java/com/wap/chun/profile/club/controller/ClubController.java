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

    @GetMapping(value = "/info/club-name/{clubName}/club-location/{clubLocation}")
    public ClubInfoDto getClubInfo(@PathVariable String clubName, @PathVariable String clubLocation){
        return clubService.getClubInfo(clubName, clubLocation);
    }

    @GetMapping(value = "/info/club-name/{clubName}")
    public List<ClubInfoDto> findByClubName(@PathVariable String clubName){
        return clubService.findByClubName(clubName);
    }

    @GetMapping(value = "/info/club-location/{clubLocation}")
    public List<ClubInfoDto> findByLocation(@PathVariable String clubLocation){
        return clubService.findByLocation(clubLocation);
    }

    @GetMapping(value = "/member/club-name/{clubName}/club-location/{clubLocation}/type/{type}")
    public List<ClubMemberDto> getClubMembers(@RequestHeader(name = "Authorize") String token, @PathVariable String clubName, @PathVariable String clubLocation, @PathVariable ClubMemberType type){
        return clubMemberService.getClubMembers(clubName, clubLocation, type);
    }

    @PostMapping(value = "/member/save")
    public void saveClubMembers(@RequestHeader(name = "Authorize") String token, @RequestBody ClubMemberSaveDto dto){
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

    @DeleteMapping(value = "/delete/club-name/{clubName}/club-location/{clubLocation}")
    public void deleteClub(@PathVariable String clubName, @PathVariable String clubLocation){
        clubService.deleteClub(clubName, clubLocation);
    }

}

