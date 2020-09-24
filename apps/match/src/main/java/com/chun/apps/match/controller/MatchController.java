package com.chun.apps.match.controller;


import com.chun.apps.match.dtos.params.ClubInfoDateParam;
import com.chun.apps.match.dtos.params.ClubInfoParam;
import com.chun.apps.match.dtos.params.ClubsInfoParam;
import com.chun.apps.match.dtos.params.LocationParam;
import com.chun.commons.dtos.match.MatchDetailsResDto;
import com.chun.commons.dtos.match.MatchResDto;
import com.chun.apps.match.dtos.request.MatchSaveReqDto;
import com.chun.apps.match.dtos.request.MatchUpdateReqDto;
import com.chun.apps.match.dtos.request.MatchScoreUpdateReqDto;
import com.chun.apps.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public void saveMatch(@RequestBody MatchSaveReqDto dto) {
        matchService.save(dto);
    }

    @GetMapping("/location")
    public List<MatchResDto> getMatchInfoByLocationInMonth(@ModelAttribute LocationParam dto) {
        return matchService.findByLocationInMonth(dto);
    }

    @GetMapping("/club")
    public List<MatchResDto> getMatchInfoByClubInfoAndDate(@ModelAttribute ClubInfoDateParam dto) {
        return matchService.findByClubNameAndClubLocation(dto);
    }

    @GetMapping("/club/details")
    public List<MatchDetailsResDto> getMatchByClubInfo(@ModelAttribute ClubInfoParam dto) {
        return matchService.findClubHistory(dto);
    }

    @GetMapping("/clubs")
    public List<MatchResDto> getTwoClubMatchInfoByClubInfo(@ModelAttribute ClubsInfoParam dto) {
        return matchService.findTwoClubHistory(dto);
    }

    @GetMapping("clubs/details")
    public List<MatchDetailsResDto> getTwoClubMatchDetailsInfoByClubInfo(@ModelAttribute ClubsInfoParam dto) {
        return matchService.findTwoClubDetailsHistory(dto);
    }

    @PutMapping
    public void updateInfo(@RequestHeader(name = "Authorization") String token, @RequestBody MatchUpdateReqDto dto) {
        matchService.update(token, dto);
    }

    @PutMapping("/result")
    public void updateInfoAfterMatch(@RequestHeader(name = "Authorization") String token, @RequestBody MatchScoreUpdateReqDto dto) {
        matchService.updateScore(token, dto);
    }
}
