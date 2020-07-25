package com.wap.chun.match.controller;

import com.wap.chun.match.dtos.MatchInfoDto;
import com.wap.chun.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/clubName/{clubName}/clubCity/{clubCity}/clubDistrict/{clubDistrict}/from/{from}/to/{to}")
    public List<MatchInfoDto> getMatchInfoByClubInfoAndDate(@PathVariable String clubName, @PathVariable String clubCity, @PathVariable String clubDistrict,
                                                            @PathVariable LocalDate from, @PathVariable LocalDate to){
        return matchService.findByClubNameAndClubLocation(clubName, clubCity, clubDistrict, from, to);
    }
}
