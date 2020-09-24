package com.wap.api.match.controller;

import com.wap.api.match.dtos.params.ClubInfoDateParam;
import com.wap.api.match.dtos.params.ClubInfoParam;
import com.wap.api.match.dtos.params.ClubsInfoParam;
import com.wap.api.match.dtos.params.LocationParam;
import com.wap.api.match.dtos.reponse.MatchDetailsInfoDto;
import com.wap.api.match.dtos.reponse.MatchInfoDto;
import com.wap.api.match.dtos.request.MatchSaveDto;
import com.wap.api.match.dtos.request.MatchUpdateInfoDto;
import com.wap.api.match.dtos.request.MatchUpdateBasicInfoDto;
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

    @PostMapping
    public void saveMatch(@RequestBody MatchSaveDto dto) {
        matchService.saveMatch(dto);
    }

    @GetMapping("/location")
    public List<MatchInfoDto> getMatchInfoByLocationInMonth(@ModelAttribute LocationParam dto) {
        return matchService.findByLocationInMonth(dto);
    }

    @GetMapping("/club")
    public List<MatchInfoDto> getMatchInfoByClubInfoAndDate(@ModelAttribute ClubInfoDateParam dto) {
        return matchService.findByClubNameAndClubLocation(dto);
    }

    @GetMapping("/club/details")
    public List<MatchDetailsInfoDto> getMatchByClubInfo(@ModelAttribute ClubInfoParam dto) {
        return matchService.findClubHistory(dto);
    }

    @GetMapping("/clubs")
    public List<MatchInfoDto> getTwoClubMatchInfoByClubInfo(@ModelAttribute ClubsInfoParam dto) {
        return matchService.findTwoClubHistory(dto);
    }

    @GetMapping("clubs/details")
    public List<MatchDetailsInfoDto> getTwoClubMatchDetailsInfoByClubInfo(@ModelAttribute ClubsInfoParam dto) {
        return matchService.findTwoClubDetailsHistory(dto);
    }

    @PutMapping
    public void updateInfo(@RequestHeader(name = "Authorization") String token, @RequestBody MatchUpdateBasicInfoDto dto) {
        matchService.updateInfo(token, dto);
    }

    @PutMapping("/result")
    public void updateInfoAfterMatch(@RequestHeader(name = "Authorization") String token, @RequestBody MatchUpdateInfoDto dto) {
        matchService.updateInfoAfterMatch(token, dto);
    }
}
