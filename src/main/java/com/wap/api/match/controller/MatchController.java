package com.wap.api.match.controller;

import com.wap.api.match.dtos.params.ClubInfoReqParam;
import com.wap.api.match.dtos.params.ClubsInfoReqParam;
import com.wap.api.match.dtos.reponse.MatchDetailsInfoDto;
import com.wap.api.match.dtos.reponse.MatchInfoDto;
import com.wap.api.match.dtos.request.MatchSaveDto;
import com.wap.api.match.dtos.request.MatchUpdateInfoDto;
import com.wap.api.match.dtos.request.MatchUpdateSimpleInfoDto;
import com.wap.api.match.service.MatchService;
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

    @GetMapping("/from/{from}/to/{to}")
    public List<MatchInfoDto> getMatchInfoByClubInfoAndDate(@ModelAttribute ClubInfoReqParam dto
            , @PathVariable LocalDate from, @PathVariable LocalDate to) {
        return matchService.findByClubNameAndClubLocation(dto, from, to);
    }

    @GetMapping("/clubName/{clubName}/clubCity/{clubCity}/clubDistrict/{clubDistrict}")
    public List<MatchDetailsInfoDto> getMatchByClubInfo(@PathVariable String clubName, @PathVariable String clubCity, @PathVariable String clubDistrict) {
        return matchService.findClubHistory(clubName, clubCity, clubDistrict);
    }

    @GetMapping("/clubs")
    public List<MatchInfoDto> getTwoClubMatchInfoByClubInfo(@ModelAttribute ClubsInfoReqParam dto) {
        return matchService.findTwoClubHistory(dto);
    }

    @GetMapping("/details/clubs")
    public List<MatchDetailsInfoDto> getTwoClubMatchDetailsInfoByClubInfo(@ModelAttribute ClubsInfoReqParam dto) {
        return matchService.findTwoClubDetailsHistory(dto);
    }

    @PutMapping("/update")
    public void updateInfo(@RequestHeader(name = "Authorization") String token, @RequestBody MatchUpdateSimpleInfoDto dto) {
        matchService.updateInfo(token, dto);
    }

    @PutMapping("/update/after/match")
    public void updateInfoAfterMatch(@RequestHeader(name = "Authorization") String token, @RequestBody MatchUpdateInfoDto dto) {
        matchService.updateInfoAfterMatch(token, dto);
    }
}
