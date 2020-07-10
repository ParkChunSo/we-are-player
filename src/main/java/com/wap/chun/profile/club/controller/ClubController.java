package com.wap.chun.profile.club.controller;

import com.wap.chun.domain.enums.ClubMemberType;
import com.wap.chun.profile.club.dtos.ClubInfoDto;
import com.wap.chun.profile.club.dtos.ClubInfoUpdateDto;
import com.wap.chun.profile.club.dtos.ClubLeaderUpdateDto;
import com.wap.chun.profile.club.dtos.ClubMemberDto;
import com.wap.chun.profile.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/club")
public class ClubController {
    private final ClubService clubService;

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
    public List<ClubMemberDto> getClubMembers(@PathVariable String clubName, @PathVariable String clubLocation, @PathVariable ClubMemberType type){
        return clubService.getClubMembers(clubName, clubLocation, type);
    }

    @PostMapping(value = "/update/leader")
    public void updateLeader(@RequestBody ClubLeaderUpdateDto dto){
        clubService.updateClubLeader(dto);
    }

    @PostMapping(value = "/update")
    public void updateClub(@RequestBody ClubInfoUpdateDto dto){
        clubService.updateClubLogoUri(dto);
        clubService.updateLikeAndRudeCnt(dto);
    }

    @DeleteMapping(value = "/delete/club-name/{clubName}/club-location/{clubLocation}")
    public void deleteClub(@PathVariable String clubName, @PathVariable String clubLocation){
        clubService.deleteClub(clubName, clubLocation);
    }

}

