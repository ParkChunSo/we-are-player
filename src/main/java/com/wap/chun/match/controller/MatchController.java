package com.wap.chun.match.controller;

import com.wap.chun.match.dtos.*;
import com.wap.chun.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;

    @PostMapping("/save")
    public void saveMatch(@RequestBody MatchSaveDto dto) {
        matchService.saveMatch(dto);
    }

    @GetMapping("/location/city/{city}/district/{district}")
    public List<MatchInfoDto> getMatchInfoByLocationInMonth(@PathVariable String city, @PathVariable String district) {
        return matchService.findByLocationInMonth(city, district);
    }

    @GetMapping("/clubName/{clubName}/clubCity/{clubCity}/clubDistrict/{clubDistrict}/from/{from}/to/{to}")
    public List<MatchInfoDto> getMatchInfoByClubInfoAndDate(@PathVariable String clubName, @PathVariable String clubCity, @PathVariable String clubDistrict,
                                                            @PathVariable LocalDate from, @PathVariable LocalDate to) {
        return matchService.findByClubNameAndClubLocation(clubName, clubCity, clubDistrict, from, to);
    }

    @GetMapping("/clubName/{clubName}/clubCity/{clubCity}/clubDistrict/{clubDistrict}")
    public List<MatchDetailsInfoDto> getMatchByClubInfo(@PathVariable String clubName, @PathVariable String clubCity, @PathVariable String clubDistrict) {
        return matchService.findClubHistory(clubName, clubCity, clubDistrict);
    }

    @GetMapping("/club1/clubName/{clubName1}/clubCity/{clubCity1}/clubDistrict/{clubDistrict1}/club2/clubName/{clubName2}/clubCity/{clubCity2}/clubDistrict/{clubDistrict2}")
    public List<MatchInfoDto> getTwoClubMatchInfoByClubInfo(@PathVariable String clubName1, @PathVariable String clubCity1, @PathVariable String clubDistrict1
            , @PathVariable String clubName2, @PathVariable String clubCity2, @PathVariable String clubDistrict2) {
        return matchService.findTwoClubHistory(clubName1, clubCity1, clubDistrict1, clubName2, clubCity2, clubDistrict2);
    }

    @GetMapping("/details/club1/clubName/{clubName1}/clubCity/{clubCity1}/clubDistrict/{clubDistrict1}/club2/clubName/{clubName2}/clubCity/{clubCity2}/clubDistrict/{clubDistrict2}")
    public List<MatchDetailsInfoDto> getTwoClubMatchDetailsInfoByClubInfo(@PathVariable String clubName1, @PathVariable String clubCity1, @PathVariable String clubDistrict1
            , @PathVariable String clubName2, @PathVariable String clubCity2, @PathVariable String clubDistrict2) {
        return matchService.findTwoClubDetailsHistory(clubName1, clubCity1, clubDistrict1, clubName2, clubCity2, clubDistrict2);
    }

    @PutMapping("/update")
    public void updateInfo(@RequestHeader(name = "Authorization") String token, @RequestBody MatchUpdateSimpleInfoDto dto){
        matchService.updateInfo(token, dto);
    }

    @PutMapping("/update/after/match")
    public void updateInfoAfterMatch(@RequestHeader(name = "Authorization") String token, @RequestBody MatchUpdateInfoDto dto){
        matchService.updateInfoAfterMatch(token, dto);
    }
}
