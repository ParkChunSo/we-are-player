package com.wap.chun.match.service;

import com.wap.chun.domain.entitys.Club;
import com.wap.chun.domain.entitys.Match;
import com.wap.chun.error.exception.ClubNotFoundException;
import com.wap.chun.error.exception.MatChNotFoundException;
import com.wap.chun.match.dtos.MatchInfoDto;
import com.wap.chun.match.repository.MatchRepository;
import com.wap.chun.profile.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository matchRepository;
    private final ClubRepository clubRepository;
    public List<MatchInfoDto> findByClubNameAndClubLocation(String clubName, String clubCity, String clubDistrict,
                                                            LocalDate from, LocalDate to){
        Club club = clubRepository.findByClubNameAndCityAndDistrictAndDeleteFlagFalse(clubName, clubCity, clubDistrict)
                .orElseThrow(ClubNotFoundException::new);
        List<Match> match = matchRepository.findByHomeClubOrAwayClubAndDateBetween(club, club, from.atStartOfDay(), to.atTime(23, 59))
                .orElseThrow(MatChNotFoundException::new);

        return match.stream()
                .map(MatchInfoDto::new)
                .collect(Collectors.toList());
    }
}
